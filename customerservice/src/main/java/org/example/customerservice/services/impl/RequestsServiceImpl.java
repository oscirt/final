package org.example.customerservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.customerservice.entity.Request;
import org.example.customerservice.repository.RequestsRepository;
import org.example.customerservice.services.RequestsService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация интерфейса сервиса {@link RequestsService}
 * для управления запросами на открытие депозита
 */
@Service
@RequiredArgsConstructor
public class RequestsServiceImpl implements RequestsService {

    private final RequestsRepository requestsRepository;

    /**
     * Получения запроса по его идентификатору
     * @param id идентификатор запроса
     * @return запрос по определенному идентификатору
     */
    @Override
    @Transactional(readOnly = true)
    public Request getRequestById(Long id) {
        return requestsRepository.findById(id)
                .orElseThrow(() -> new IntentionException(String.format("Заявка с идентификатором %d не найдена.", id)));
    }

    /**
     * Получение всех запросов определенного клиента
     * @param customerId идентификатор пользователя
     * @return список запросов определенного пользователя
     */
    @Override
    @Transactional(readOnly = true)
    public List<Request> getAllRequestsByCustomerId(Long customerId) {
        return requestsRepository.findAllByCustomerId(customerId);
    }

    /**
     * Создание запроса на открытие депозита
     * @param request запрос на открытие депозита
     * @return запрос после создания
     */
    @Override
    @Transactional
    public Request createRequest(Request request) {
        if (requestsRepository.existsById(request.getId())) {
            throw new IntentionException(String.format("Заявка с идентификатором %d уже существует.", request.getId()));
        }
        return requestsRepository.save(request);
    }
}
