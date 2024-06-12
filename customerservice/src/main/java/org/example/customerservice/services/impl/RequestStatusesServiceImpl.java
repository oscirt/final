package org.example.customerservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.customerservice.entity.RequestStatus;
import org.example.customerservice.repository.RequestStatusesRepository;
import org.example.customerservice.services.RequestStatusesService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация интерфейса сервиса {@link RequestStatusesService}
 * для управления статусами запросов
 */
@Service
@RequiredArgsConstructor
public class RequestStatusesServiceImpl implements RequestStatusesService {

    private final RequestStatusesRepository requestStatusesRepository;

    /**
     * Получение списка статусов запросов
     * @return список статусов запросов
     */
    @Override
    @Transactional(readOnly = true)
    public List<RequestStatus> getAllRequestStatuses() {
        return requestStatusesRepository.findAll();
    }

    /**
     * Получение статуса запроса по id
     * @param requestStatusId идентификатор статуса запроса
     * @return статус запроса
     */
    @Override
    public RequestStatus getRequestStatus(Integer requestStatusId) {
        return requestStatusesRepository.findById(requestStatusId)
                .orElseThrow(() -> new IntentionException("Статус не найден."));
    }
}
