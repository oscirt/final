package org.example.starter.client;

import org.example.starter.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Клиент для отправления запросов сервису account
 */
@FeignClient(name = "accounts")
public interface AccountServiceClient {

    /**
     * Отправление запроса на получение информации о счете
     * @param jwtToken токен авторизации
     * @return данные о счете
     */
    @GetMapping(path = "/account/current")
    AccountDto getAccount(@RequestHeader("Authorization") String jwtToken);

    /**
     * Отправление запроса на пополнение счета
     * @param jwtToken токен авторизации
     * @param amount сумма пополнения
     * @return данные о счете после пополнения
     */
    @PostMapping(path = "/account/current/topUp")
    AccountDto topUpAccount(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam(value = "amount") BigDecimal amount
    );

    /**
     * Отправление запроса на списание денег со счета
     * @param jwtToken токен авторизации
     * @param amount сумма списания
     * @return данные о счете после списания
     */
    @PostMapping(path = "/account/current/writeOff")
    AccountDto writeOffAccount(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam(value = "amount") BigDecimal amount
    );
}
