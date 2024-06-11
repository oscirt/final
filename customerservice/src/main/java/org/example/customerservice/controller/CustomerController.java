package org.example.customerservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.customerservice.entity.CurrentRequestStatus;
import org.example.customerservice.entity.Customer;
import org.example.customerservice.mapper.CurrentRequestStatusMapper;
import org.example.customerservice.mapper.CustomerMapper;
import org.example.customerservice.mapper.RequestMapper;
import org.example.customerservice.mapper.RequestStatusMapper;
import org.example.customerservice.services.CurrentRequestStatusesService;
import org.example.customerservice.services.RequestStatusesService;
import org.example.customerservice.services.RequestsService;
import org.example.customerservice.services.impl.CustomerServiceImpl;
import org.example.starter.dto.CurrentRequestStatusDto;
import org.example.starter.dto.CustomerDto;
import org.example.starter.dto.RequestDto;
import org.example.starter.dto.RequestStatusDto;
import org.example.starter.exception.IntentionException;
import org.example.starter.exception.UnauthorizedException;
import org.example.starter.jwt.BasicJwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CurrentRequestStatusesService currentRequestStatusesService;
    private final RequestsService requestsService;
    private final RequestStatusesService requestStatusesService;
    private final CustomerServiceImpl customerService;

    private final BasicJwtUtils basicJwtUtils;

    @GetMapping("/info")
    public ResponseEntity<CustomerDto> getCustomerInfo(@RequestHeader("Authorization") String jwtToken) {
        Customer customer = getCustomerFromJwtToken(jwtToken);
        return ResponseEntity.ok(CustomerMapper.toDto(customer));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<RequestDto>> getCustomerRequests(@RequestHeader("Authorization") String jwtToken) {
        Customer customer = getCustomerFromJwtToken(jwtToken);
        return ResponseEntity.ok(RequestMapper.toDto(requestsService.getAllRequestsByCustomerId(customer.getId())));
    }

    @PostMapping("/requests")
    public ResponseEntity<RequestDto> createCustomerRequest(@RequestHeader("Authorization") String jwtToken) {
        Customer customer = getCustomerFromJwtToken(jwtToken);
        return ResponseEntity.ok(new RequestDto());
    }

    @GetMapping("/requests/{requestId}/statusLatest")
    public ResponseEntity<CurrentRequestStatusDto> getCurrentRequestStatus(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable(name = "requestId") String requestId
    ) {
        Customer customer = getCustomerFromJwtToken(jwtToken);
        CurrentRequestStatus currentStatus = currentRequestStatusesService
                .getCurrentRequestStatusByRequestId(Long.parseLong(requestId));
        if (!currentStatus.getRequest().getCustomer().getId().equals(customer.getId())) {
            throw new IntentionException(String.format("Запрос с идентификатором %s не найден.", requestId));
        }
        return ResponseEntity.ok(CurrentRequestStatusMapper.toDto(currentStatus));
    }

    @GetMapping("/requests/{requestId}/statusHistory")
    public ResponseEntity<List<CurrentRequestStatusDto>>getCurrentRequestStatusHistory(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable(name = "requestId") String requestId
    ) {
        Customer customer = getCustomerFromJwtToken(jwtToken);
        List<CurrentRequestStatus> currentRequestStatuses = currentRequestStatusesService
                .getRequestStatusHistoryByRequestId(Long.parseLong(requestId));
        if (!currentRequestStatuses.isEmpty() &&
                !currentRequestStatuses.get(0).getRequest().getCustomer().getId().equals(customer.getId())) {
            throw new IntentionException(String.format("Запрос с идентификатором %s не найден.", requestId));
        }
        return ResponseEntity.ok(CurrentRequestStatusMapper.toDto(currentRequestStatuses));
    }

    @GetMapping("/requestStatuses")
    public ResponseEntity<List<RequestStatusDto>> getRequestStatuses(@RequestHeader("Authorization") String jwtToken) {
        return ResponseEntity.ok(RequestStatusMapper.toDto(requestStatusesService.getAllRequestStatuses()));
    }

    private Customer getCustomerFromJwtToken(String jwtToken) {
        if (!basicJwtUtils.validateJwtToken(jwtToken)) {
            throw new UnauthorizedException("JWT токен не валидный.");
        }
        String username = basicJwtUtils.getUsernameFromJwtToken(jwtToken);
        return (Customer) customerService.loadUserByUsername(username);
    }
}
