package org.example.starter.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.starter.dto.DepositDto;
import org.example.starter.dto.DepositPercentTypeDto;
import org.example.starter.dto.DepositTypeDto;
import org.example.starter.exception.InnerException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * HTTP клиент для отправления запросов сервису deposits
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class DepositServiceClient {

    private static final String SERVICE_NAME = "deposits";

    private static final String GET_CUSTOMER_DEPOSITS = "/deposits/all";
    private static final String GET_DEPOSIT_WITH_ID = "/deposits/withId/{}";
    private static final String POST_NEW_DEPOSIT = "/deposits";
    private static final String GET_DEPOSIT_TYPES = "/deposits/types";
    private static final String GET_DEPOSIT_PERCENT_TYPES = "/deposits/percentTypes";

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    /**
     * Получение всех депозитов клиента
     * @param jwtToken токен авторизации
     * @return список депозитов
     */
    public DepositDto[] getDeposits(String jwtToken) {
        log.info("Отправляем запрос сервису {} на получение информации о депозитах клиента", SERVICE_NAME);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<DepositDto[]> entity = new HttpEntity<>(headers);

        ResponseEntity<DepositDto[]> depositsDto = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + GET_CUSTOMER_DEPOSITS)
                        .toUriString(),
                HttpMethod.GET,
                entity,
                DepositDto[].class
        );

        return depositsDto.getBody();
    }

    /**
     * Получение депозита клиента с определенным id
     * @param jwtToken токен авторизации
     * @param depositId идентификатор депозита
     * @return депозит клиента
     */
    public DepositDto getDepositWithId(String jwtToken, String depositId) {
        log.info("Отправляем запрос сервису {} на получение информации о депозите клиента с id {}",
                SERVICE_NAME, depositId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<DepositDto> entity = new HttpEntity<>(headers);

        String getDepositWithId = GET_DEPOSIT_WITH_ID.replaceFirst("\\{}", depositId);

        ResponseEntity<DepositDto> depositDto = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + getDepositWithId)
                        .toUriString(),
                HttpMethod.GET,
                entity,
                DepositDto.class
        );

        return depositDto.getBody();
    }

    /**
     * Отправление запроса на создание объекта депозита клиента
     * @param jwtToken токен авторизации
     * @param depositDto объект депозита который нужно сохранить
     * @return сохраненный объект депозита
     */
    public DepositDto createNewDeposit(String jwtToken, DepositDto depositDto) {
        log.info("Отправляем запрос сервису {} на создание нового объекта депозита {}",
                SERVICE_NAME, depositDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DepositDto> entity = new HttpEntity<>(depositDto, headers);

        ResponseEntity<DepositDto> newDepositDto = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + POST_NEW_DEPOSIT)
                        .toUriString(),
                HttpMethod.POST,
                entity,
                DepositDto.class
        );

        return newDepositDto.getBody();
    }

    /**
     * Получение списка видов депозитов
     * @param jwtToken токен авторизации
     * @return список видов депозитов
     */
    public DepositTypeDto[] getDepositTypes(String jwtToken) {
        log.info("Отправляем запрос сервису {} на получение списка видов депозитов", SERVICE_NAME);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<DepositTypeDto> entity = new HttpEntity<>(headers);

        ResponseEntity<DepositTypeDto[]> depositTypes = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + GET_DEPOSIT_TYPES)
                        .toUriString(),
                HttpMethod.GET,
                entity,
                DepositTypeDto[].class
        );

        return depositTypes.getBody();
    }

    /**
     * Получение списка видов выплат
     * @param jwtToken токен авторизации
     * @return список видов выплат
     */
    public DepositPercentTypeDto[] getDepositPercentTypes(String jwtToken) {
        log.info("Отправляем запрос сервису {} на получение списка видов выплат", SERVICE_NAME);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<DepositPercentTypeDto> entity = new HttpEntity<>(headers);

        ResponseEntity< DepositPercentTypeDto[]> depositPercentTypes = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + GET_DEPOSIT_PERCENT_TYPES)
                        .toUriString(),
                HttpMethod.GET,
                entity,
                DepositPercentTypeDto[].class
        );

        return depositPercentTypes.getBody();
    }

    private ServiceInstance getServiceInstance() {
        return discoveryClient.getInstances(SERVICE_NAME)
                .stream()
                .findFirst()
                .orElseThrow(() -> new InnerException(String.format("Сервис %s недоступен.", SERVICE_NAME)));
    }
}
