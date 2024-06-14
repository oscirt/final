package org.example.starter.client;

import org.example.starter.dto.DepositDto;
import org.example.starter.dto.DepositPercentTypeDto;
import org.example.starter.dto.DepositTypeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Клиент для отправления запросов сервису deposits
 */
@FeignClient(name = "deposits")
public interface DepositServiceClient {

    /**
     * Получение всех депозитов клиента
     * @param jwtToken токен авторизации
     * @return список депозитов
     */
    @GetMapping(path = "/deposits/all")
    List<DepositDto> getDeposits(@RequestHeader("Authorization") String jwtToken);

    /**
     * Получение депозита клиента с определенным id
     * @param jwtToken токен авторизации
     * @param depositId идентификатор депозита
     * @return депозит клиента
     */
    @GetMapping(path = "/deposits/withId/{depositId}")
    DepositDto getDepositWithId(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable(name = "depositId") String depositId
    );

    /**
     * Отправление запроса на создание объекта депозита клиента
     * @param jwtToken токен авторизации
     * @param depositDto объект депозита который нужно сохранить
     * @return сохраненный объект депозита
     */
    @PostMapping(path = "/deposits", consumes = MediaType.APPLICATION_JSON_VALUE)
    DepositDto createNewDeposit(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody DepositDto depositDto
    );

    /**
     * Получение списка видов депозитов
     * @param jwtToken токен авторизации
     * @return список видов депозитов
     */
    @GetMapping(path = "/deposits/types")
    List<DepositTypeDto> getDepositTypes(@RequestHeader("Authorization") String jwtToken);

    /**
     * Получение списка видов выплат
     * @param jwtToken токен авторизации
     * @return список видов выплат
     */
    @GetMapping(path = "/deposits/percentTypes")
    List<DepositPercentTypeDto> getDepositPercentTypes(@RequestHeader("Authorization") String jwtToken);
}
