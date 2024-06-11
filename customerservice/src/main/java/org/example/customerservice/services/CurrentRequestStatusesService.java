package org.example.customerservice.services;

import org.example.customerservice.entity.CurrentRequestStatus;

import java.util.List;

/**
 * Интерфейс сервиса для управления текущим статусом заявки
 */
public interface CurrentRequestStatusesService {

    /**
     * Получение текущего статуса заявки
     * @param requestId идентификатор заявки
     * @return текущий статус заявки
     */
    CurrentRequestStatus getCurrentRequestStatusByRequestId(Long requestId);

    /**
     * Получение всех статусов определенной заявки
     * @param requestId идентификатор заявки
     * @return список статусов определенной заявки
     */
    List<CurrentRequestStatus> getRequestStatusHistoryByRequestId(Long requestId);
}
