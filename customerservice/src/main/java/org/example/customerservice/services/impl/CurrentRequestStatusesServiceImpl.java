package org.example.customerservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.customerservice.entity.CurrentRequestStatus;
import org.example.customerservice.repository.CurrentRequestStatusesRepository;
import org.example.customerservice.services.CurrentRequestStatusesService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация интерфейса сервиса {@link CurrentRequestStatusesService}
 * для управления текущим статусом заявки
 */
@Service
@RequiredArgsConstructor
public class CurrentRequestStatusesServiceImpl implements CurrentRequestStatusesService {

    private final CurrentRequestStatusesRepository currentRequestStatusesRepository;

    /**
     * Получение текущего статуса заявки
     * @param requestId идентификатор заявки
     * @return текущий статус заявки
     */
    @Override
    @Transactional(readOnly = true)
    public CurrentRequestStatus getCurrentRequestStatusByRequestId(Long requestId) {
        return currentRequestStatusesRepository.findByRequestIdOrderByChangeDatetimeDesc(requestId)
                .orElseThrow(() -> new IntentionException(String.format(
                        "Запроса с идентификатором %d не существует.",
                        requestId
                )));
    }

    /**
     * Получение всех статусов определенной заявки
     * @param requestId идентификатор заявки
     * @return список статусов определенной заявки
     */
    @Override
    @Transactional(readOnly = true)
    public List<CurrentRequestStatus> getRequestStatusHistoryByRequestId(Long requestId) {
        return currentRequestStatusesRepository.findAllByRequestId(requestId);
    }
}
