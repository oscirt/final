package org.example.api_gateway.controller;

import lombok.RequiredArgsConstructor;
import org.example.api_gateway.entity.request.DepositOptionsRequest;
import org.example.api_gateway.entity.response.DepositOptionsResponse;
import org.example.api_gateway.service.DepositsService;
import org.example.starter.client.CustomerServiceClient;
import org.example.starter.client.DepositServiceClient;
import org.example.starter.dto.CustomerDto;
import org.example.starter.dto.DepositDto;
import org.example.starter.dto.RequestDto;
import org.example.starter.exception.IntentionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestsController {

    private final CustomerServiceClient customerServiceClient;
    private final DepositsService depositsService;
    private final DepositServiceClient depositServiceClient;

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
        DepositOptionsResponse calculationResults = depositsService.calculate(request);
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
        return ResponseEntity.ok(result);
    }
}
