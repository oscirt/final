package org.example.starter.client;

import org.example.starter.dto.CurrentRequestStatusDto;
import org.example.starter.dto.CustomerDto;
import org.example.starter.dto.RequestDto;
import org.example.starter.dto.RequestStatusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Клиент для отправления запросов сервису customerservice
 */
@FeignClient(name = "customerservice")
public interface CustomerServiceClient {

    /**
     * Отправление запроса на получение информации о клиенте по его псевдониму
     * @param jwtToken токен авторизации
     * @return информацию о клиенте
     */
    @GetMapping(path = "/customer/info")
    CustomerDto getCustomerInfoByUsername(@RequestHeader("Authorization") String jwtToken);

    /**
     * Отправление запроса на получение информации об отправленных заявках на открытие депозита
     * @param jwtToken токен авторизации
     * @return список отправленных заявок
     */
    @GetMapping(path = "/customer/requests")
    List<RequestDto> getCustomerRequests(@RequestHeader("Authorization") String jwtToken);

    /**
     * Отправление запроса на добавление новой заявки на открытие депозита
     * @param jwtToken токен авторизации
     * @param requestDto данные заявки
     * @return информация о добавленной заявке
     */
    @PostMapping(path = "/customer/requests", consumes = MediaType.APPLICATION_JSON_VALUE)
    RequestDto postNewRequest(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody() RequestDto requestDto
    );

    /**
     * Получение текущего статуса заявки
     * @param jwtToken токен авторизации
     * @param requestId идентификатор заявки
     * @return данные о текущем статусе заявки
     */
    @GetMapping(path = "/customer/requests/{requestId}/statusLatest")
    CurrentRequestStatusDto getCurrentRequestStatus(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable(name = "requestId") String requestId
    );

    /**
     * Получение списка всех статусов для определенной заявки
     * @param jwtToken токен авторизации
     * @param requestId идентификатор заявки
     * @return массив статусов для определенной заявки
     */
    @GetMapping(path = "/customer/requests/{requestId}/statusHistory")
    List<CurrentRequestStatusDto> getCurrentRequestStatusHistory(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable(name = "requestId") String requestId
    );

    /**
     * Получение всех возможных статусов для заявки
     * @param jwtToken токен авторизации
     * @return список возможных статусов для заявки
     */
    @GetMapping(path = "/customer/requestStatuses")
    List<RequestStatusDto> getRequestStatuses(@RequestHeader("Authorization") String jwtToken);
}
