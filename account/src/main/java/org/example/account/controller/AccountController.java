package org.example.account.controller;

import lombok.RequiredArgsConstructor;
import org.example.account.entity.Account;
import org.example.starter.dto.AccountDto;
import org.example.account.mapper.AccountMapper;
import org.example.account.service.AccountService;
import org.example.starter.client.CustomerServiceClient;
import org.example.starter.dto.CustomerDto;
import org.example.starter.jwt.BasicJwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Контроллер для управления счетом
 */
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final BasicJwtUtils jwtUtils;

    private final CustomerServiceClient customerServiceClient;

    /**
     * Эндпоинт получения информации о счете
     * @param jwtToken токен авторизации
     * @return данные о счете
     */
    @GetMapping("/current")
    public ResponseEntity<AccountDto> getAccount(@RequestHeader("Authorization") String jwtToken) {
        CustomerDto customerDto = customerServiceClient.getCustomerInfoByUsername(jwtToken);
        Account account = accountService.getAccountById(customerDto.getBankAccountId());
        return ResponseEntity
                .ok()
                .body(AccountMapper.toDto(account));
    }

    /**
     * Эндпоинт пополнения счета
     * @param jwtToken токен авторизации
     * @param amount сумма пополнения счета
     * @return счет после пополнения
     */
    @PostMapping("/current/topUp")
    public ResponseEntity<AccountDto> topUpAccount(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam Double amount
    ) {
        CustomerDto customerDto = customerServiceClient.getCustomerInfoByUsername(jwtToken);
        Account account = accountService.topUpAccount(customerDto.getBankAccountId(), BigDecimal.valueOf(amount));
        return ResponseEntity
                .ok()
                .body(AccountMapper.toDto(account));
    }

    /**
     * Эндпоинт списания со счета
     * @param jwtToken токен авторизации
     * @param amount сумма списания со счета
     * @return счет после списания
     */
    @PostMapping("/current/writeOff")
    public ResponseEntity<AccountDto> writeOffAccount(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam Double amount
    ) {
        CustomerDto customerDto = customerServiceClient.getCustomerInfoByUsername(jwtToken);
        Account account = accountService.writeOffAccount(customerDto.getBankAccountId(), BigDecimal.valueOf(amount));
        return ResponseEntity
                .ok()
                .body(AccountMapper.toDto(account));
    }
}
