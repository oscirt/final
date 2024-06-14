package org.example.deposit.controller;

import lombok.RequiredArgsConstructor;
import org.example.deposit.entity.DepositOptionsRequest;
import org.example.deposit.entity.DepositOptionsResponse;
import org.example.deposit.entity.DepositPercentType;
import org.example.deposit.entity.DepositType;
import org.example.deposit.mapper.DepositMapper;
import org.example.deposit.mapper.DepositPercentTypeMapper;
import org.example.deposit.mapper.DepositTypeMapper;
import org.example.deposit.service.DepositPercentTypeService;
import org.example.deposit.service.DepositService;
import org.example.deposit.service.DepositTypeService;
import org.example.starter.client.AccountServiceClient;
import org.example.starter.client.CustomerServiceClient;
import org.example.starter.client.DepositServiceClient;
import org.example.starter.dto.*;
import org.example.starter.exception.IntentionException;
import org.example.starter.exception.UnauthorizedException;
import org.example.starter.jwt.BasicJwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;
    private final DepositTypeService depositTypeService;
    private final DepositPercentTypeService depositPercentTypeService;
    private final BasicJwtUtils basicJwtUtils;
    private final CustomerServiceClient customerServiceClient;
    private final AccountServiceClient accountServiceClient;
    private final DepositServiceClient depositServiceClient;

    @GetMapping("/all")
    public ResponseEntity<List<DepositDto>> getCustomerDeposits(@RequestHeader("Authorization") String jwtToken) {
        validateJwtToken(jwtToken);
        CustomerDto customerDto = customerServiceClient.getCustomerInfoByUsername(jwtToken);
        return ResponseEntity.ok(DepositMapper.toDto(depositService.getCustomerDeposits(customerDto.getId())));
    }

    @GetMapping("/withId/{depositId}")
    public ResponseEntity<DepositDto> getDepositWithId(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable(name = "depositId") Long depositId) {
        validateJwtToken(jwtToken);
        CustomerDto customerDto = customerServiceClient.getCustomerInfoByUsername(jwtToken);
        return ResponseEntity.ok(DepositMapper.toDto(depositService.getDepositInfo(depositId, customerDto.getId())));
    }

    @PostMapping
    public ResponseEntity<DepositDto> createNewDeposit(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody DepositDto dto) {
        validateJwtToken(jwtToken);
        CustomerDto customerDto = customerServiceClient.getCustomerInfoByUsername(jwtToken);
        DepositType depositType = depositTypeService.getDepositType(dto.getDepositTypeId());
        DepositPercentType percentType = depositPercentTypeService.getDepositPercentType(dto.getDepositPercentTypeId());
        return ResponseEntity.ok(DepositMapper.toDto(depositService.createNewDeposit(DepositMapper.toEntity(
                dto,
                customerDto.getId(),
                depositType,
                percentType
        ))));
    }

    @GetMapping("/types")
    public ResponseEntity<List<DepositTypeDto>> getAllDepositTypes(@RequestHeader("Authorization") String jwtToken) {
        validateJwtToken(jwtToken);
        return ResponseEntity.ok(DepositTypeMapper.toDto(depositTypeService.getAllDepositTypes()));
    }

    @GetMapping("/percentTypes")
    public ResponseEntity<List<DepositPercentTypeDto>> getAllDepositPercentTypes(
            @RequestHeader("Authorization") String jwtToken) {
        validateJwtToken(jwtToken);
        return ResponseEntity.ok(DepositPercentTypeMapper.toDto(depositPercentTypeService.getAllDepositPercentTypes()));
    }

    @PostMapping("/calculate")
    public ResponseEntity<DepositOptionsResponse> calculate(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody DepositOptionsRequest request) {
        validateJwtToken(jwtToken);
        return ResponseEntity.ok(depositService.calculate(request));
    }

    @PostMapping("/new")
    public ResponseEntity<RequestDto> newRequest(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody DepositOptionsRequest request
    ) {
        CustomerDto customerDto = customerServiceClient.getCustomerInfoByUsername(jwtToken);
        Boolean isRefill = request.getDepositTypeId() == 1 || request.getDepositTypeId() == 2;
        LocalDate nextPaymentDate = LocalDate.now();
        switch (request.getDepositPercentTypeId()) {
            case 1:
                nextPaymentDate = nextPaymentDate.plusMonths(1);
                break;
            case 2:
                nextPaymentDate = nextPaymentDate.plusMonths(request.getDepositMonths());
                break;
            default:
                throw new IntentionException(String.format("Вида выплаты по вкладу %d нет.",
                        request.getDepositPercentTypeId()));
        }
        AccountDto accountDto = accountServiceClient.getAccount(jwtToken);
        if (accountDto.getAmount().doubleValue() < request.getDepositAmount().doubleValue()) {
            throw new IntentionException("Сумма вклада больше, чем имеется на счету!");
        }
        DepositOptionsResponse calculationResults = depositService.calculate(request);
        DepositDto depositDto = new DepositDto(
                0L,
                customerDto.getId(),
                customerDto.getBankAccountId(),
                request.getDepositTypeId(),
                isRefill,
                request.getDepositAmount(),
                LocalDate.now(),
                LocalDate.now().plusMonths(request.getDepositMonths()),
                calculationResults.getDepositRate(),
                request.getDepositPercentTypeId(),
                nextPaymentDate,
                customerDto.getBankAccountId(),
                request.getIsCapitalization(),
                customerDto.getBankAccountId()
        );
        DepositDto createdDepositDto = depositServiceClient.createNewDeposit(jwtToken, depositDto);
        RequestDto requestDto = new RequestDto(
                0L,
                customerDto.getId(),
                LocalDate.now(),
                createdDepositDto.getId()
        );
        RequestDto result = customerServiceClient.postNewRequest(jwtToken, requestDto);
        accountServiceClient.writeOffAccount(jwtToken, request.getDepositAmount());
        return ResponseEntity.ok(result);
    }

    private void validateJwtToken(String jwtToken) {
        if (!basicJwtUtils.validateJwtToken(jwtToken)) {
            throw new UnauthorizedException("Валидация токена авторизации прошла неудачно!");
        }
    }
}
