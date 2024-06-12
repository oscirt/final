package org.example.customerservice.services;

import org.example.customerservice.entity.RequestStatus;

import java.util.List;

/**
 * Интерфейс сервиса для управления статусами запросов
 */
public interface RequestStatusesService {

    /**
     * Получение списка статусов запросов
     * @return список статусов запросов
     */
    List<RequestStatus> getAllRequestStatuses();

    /**
     * Получение статуса запроса по id
     * @param requestStatusId идентификатор статуса запроса
     * @return статус запроса
     */
    RequestStatus getRequestStatus(Integer requestStatusId);
}
