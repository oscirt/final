package org.example.customerservice.services;

import org.example.customerservice.entity.Request;

import java.util.List;

/**
 * Интерфейс сервиса для управления запросами на открытие депозита
 */
public interface RequestsService {

    /**
     * Получения запроса по его идентификатору
     * @param id идентификатор запроса
     * @return запрос по определенному идентификатору
     */
    Request getRequestById(Long id);

    /**
     * Получение всех запросов определенного клиента
     * @param customerId идентификатор пользователя
     * @return список запросов определенного пользователя
     */
    List<Request> getAllRequestsByCustomerId(Long customerId);

    /**
     * Создание запроса на открытие депозита
     * @param request запрос на открытие депозита
     * @return запрос после создания
     */
    Request createRequest(Request request);
}
