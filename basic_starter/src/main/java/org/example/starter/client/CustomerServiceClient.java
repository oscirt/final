package org.example.starter.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.starter.dto.CurrentRequestStatusDto;
import org.example.starter.dto.CustomerDto;
import org.example.starter.dto.RequestDto;
import org.example.starter.dto.RequestStatusDto;
import org.example.starter.exception.InnerException;
import org.example.starter.exception.IntentionException;
import org.example.starter.dto.response.ErrorResponse;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * HTTP клиент для отправления запросов сервису customerservice
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class CustomerServiceClient {

    private static final String SERVICE_NAME = "customerservice";

    private static final String GET_CUSTOMER_INFO = "/customer/info";
    private static final String GET_CUSTOMER_REQUESTS = "/customer/requests";
    private static final String POST_REQUEST = "/customer/requests";
    private static final String PUT_REQUEST = "/customer/requests/{}";
    private static final String GET_CURRENT_REQUEST_STATUS = "/customer/requests/{}/statusLatest";
    private static final String GET_ALL_REQUEST_STATUSES = "/customer/requests/{}/statusHistory";
    private static final String GET_REQUEST_STATUSES = "/customer/requestStatuses";

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    /**
     * Отправление запроса на получение информации о клиенте по его псевдониму
     * @param jwtToken токен авторизации
     * @return информацию о клиенте
     */
    public CustomerDto getCustomerInfoByUsername(String jwtToken) {
        log.info("Отправляем запрос сервису {} на получение информации о пользователе", SERVICE_NAME);

        ResponseEntity<CustomerDto> customerInfo = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + GET_CUSTOMER_INFO)
                        .toUriString(),
                HttpMethod.GET,
                prepareHttpEntity(jwtToken),
                CustomerDto.class
        );

        return customerInfo.getBody();
    }

    /**
     * Отправление запроса на получение информации об отправленных заявках на открытие депозита
     * @param jwtToken токен авторизации
     * @return список отправленных заявок
     */
    public RequestDto[] getCustomerRequestsByUsername(String jwtToken) {
        log.info("Отправляем запрос сервису {} на получение информации о заявках пользователя", SERVICE_NAME);

        ResponseEntity<RequestDto[]> requestDto = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + GET_CUSTOMER_REQUESTS)
                        .toUriString(),
                HttpMethod.GET,
                prepareHttpEntity(jwtToken),
                RequestDto[].class
        );

        return requestDto.getBody();
    }

    /**
     * Отправление запроса на добавление новой заявки на открытие депозита
     * @param jwtToken токен авторизации
     * @param requestDto данные заявки
     * @return информация о добавленной заявке
     */
    public RequestDto postNewRequest(String jwtToken, RequestDto requestDto) {
        log.info("Отправляем запрос сервису {} на добавление новой заявки {}",
                SERVICE_NAME, requestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestDto> postHttpEntity = new HttpEntity<>(requestDto, headers);

        ResponseEntity<RequestDto> requestEntity = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + POST_REQUEST)
                        .toUriString(),
                HttpMethod.POST,
                postHttpEntity,
                RequestDto.class
        );

        return requestEntity.getBody();
    }

    /**
     * Получение текущего статуса заявки
     * @param jwtToken токен авторизации
     * @param requestId идентификатор заявки
     * @return данные о текущем статусе заявки
     */
    public CurrentRequestStatusDto getCurrentRequestStatus(String jwtToken, String requestId) {
        log.info("Отправляем запрос сервису {} на получение информации о текущем статусе заявки {}",
                SERVICE_NAME, requestId);

        String getCurrentRequestStatusUrl = GET_CURRENT_REQUEST_STATUS.replaceFirst("\\{}", requestId);

        ResponseEntity<CurrentRequestStatusDto> customerInfo = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + getCurrentRequestStatusUrl)
                        .toUriString(),
                HttpMethod.GET,
                prepareHttpEntity(jwtToken),
                CurrentRequestStatusDto.class
        );

        return customerInfo.getBody();
    }

    /**
     * Получение списка всех статусов для определенной заявки
     * @param jwtToken токен авторизации
     * @param requestId идентификатор заявки
     * @return массив статусов для определенной заявки
     */
    public CurrentRequestStatusDto[] getCurrentRequestStatusHistory(String jwtToken, String requestId) {
        log.info("Отправляем запрос сервису {} на получение информации о всех статусах запроса {}",
                SERVICE_NAME, requestId);

        String getAllRequestStatuses = GET_ALL_REQUEST_STATUSES.replaceFirst("\\{}", requestId);

        ResponseEntity<CurrentRequestStatusDto[]> customerInfo = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + getAllRequestStatuses)
                        .toUriString(),
                HttpMethod.GET,
                prepareHttpEntity(jwtToken),
                CurrentRequestStatusDto[].class
        );

        return customerInfo.getBody();
    }

    /**
     * Получение всех возможных статусов для заявки
     * @param jwtToken токен авторизации
     * @return список возможных статусов для заявки
     */
    public RequestStatusDto[] getRequestStatuses(String jwtToken) {
        log.info("Отправляем запрос сервису {} на получение информации о возможных статусах", SERVICE_NAME);

        ResponseEntity<RequestStatusDto[]> customerInfo = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + GET_REQUEST_STATUSES)
                        .toUriString(),
                HttpMethod.GET,
                prepareHttpEntity(jwtToken),
                RequestStatusDto[].class
        );

        return customerInfo.getBody();
    }

    private HttpEntity<String> prepareHttpEntity(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        return new HttpEntity<>(headers);
    }

    private ServiceInstance getServiceInstance() {
        return discoveryClient.getInstances(SERVICE_NAME)
                .stream()
                .findFirst()
                .orElseThrow(() -> new InnerException(String.format("Сервис %s недоступен.", SERVICE_NAME)));
    }
}
