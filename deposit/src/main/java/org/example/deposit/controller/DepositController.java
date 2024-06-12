package org.example.deposit.controller;

import lombok.RequiredArgsConstructor;
import org.example.deposit.entity.DepositPercentType;
import org.example.deposit.entity.DepositType;
import org.example.deposit.mapper.DepositMapper;
import org.example.deposit.mapper.DepositPercentTypeMapper;
import org.example.deposit.mapper.DepositTypeMapper;
import org.example.deposit.service.DepositPercentTypeService;
import org.example.deposit.service.DepositService;
import org.example.deposit.service.DepositTypeService;
import org.example.starter.client.CustomerServiceClient;
import org.example.starter.dto.CustomerDto;
import org.example.starter.dto.DepositDto;
import org.example.starter.dto.DepositPercentTypeDto;
import org.example.starter.dto.DepositTypeDto;
import org.example.starter.exception.UnauthorizedException;
import org.example.starter.jwt.BasicJwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private void validateJwtToken(String jwtToken) {
        if (!basicJwtUtils.validateJwtToken(jwtToken)) {
            throw new UnauthorizedException("Валидация токена авторизации прошла неудачно!");
        }
    }
}
