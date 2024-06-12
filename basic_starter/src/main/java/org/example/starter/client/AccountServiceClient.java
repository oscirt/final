package org.example.starter.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.starter.dto.AccountDto;
import org.example.starter.exception.InnerException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

/**
 * HTTP клиент для отправления запросов сервису account
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class AccountServiceClient {

    private static final String SERVICE_NAME = "accounts";

    private static final String GET_CUSTOMER_ACCOUNT = "/account/current";
    private static final String POST_ACCOUNT_TOP_UP = "/account/current/topUp";
    private static final String POST_ACCOUNT_WRITE_OFF = "/account/current/writeOff";

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    /**
     * Отправление запроса на получение информации о счете
     * @param jwtToken токен авторизации
     * @return данные о счете
     */
    public AccountDto getAccount(String jwtToken) {
        log.info("Отправляем запрос сервису {} на получение информации о счете клиента", SERVICE_NAME);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<AccountDto> accountDto = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + GET_CUSTOMER_ACCOUNT)
                        .toUriString(),
                HttpMethod.GET,
                entity,
                AccountDto.class
        );

        return accountDto.getBody();
    }

    /**
     * Отправление запроса на пополнение счета
     * @param jwtToken токен авторизации
     * @param amount сумма пополнения
     * @return данные о счете после пополнения
     */
    public AccountDto topUpAccount(String jwtToken, BigDecimal amount) {
        log.info("Отправляем запрос сервису {} на пополнение счета на {}", SERVICE_NAME, amount);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<Void> postHttpEntity = new HttpEntity<>(headers);

        ResponseEntity<AccountDto> accountDto = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + POST_ACCOUNT_TOP_UP)
                        .queryParam("amount", amount).toUriString(),
                HttpMethod.POST,
                postHttpEntity,
                AccountDto.class
        );

        return accountDto.getBody();
    }

    /**
     * Отправление запроса на списание денег со счета
     * @param jwtToken токен авторизации
     * @param amount сумма списания
     * @return данные о счете после списания
     */
    public AccountDto writeOffAccount(String jwtToken, BigDecimal amount) {
        log.info("Отправляем запрос сервису {} на списание со счета суммы {}", SERVICE_NAME, amount);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<Void> postHttpEntity = new HttpEntity<>(headers);

        ResponseEntity<AccountDto> accountDto = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(getServiceInstance().getUri().toString() + POST_ACCOUNT_WRITE_OFF)
                        .queryParam("amount", amount).toUriString(),
                HttpMethod.POST,
                postHttpEntity,
                AccountDto.class
        );

        return accountDto.getBody();
    }

    private ServiceInstance getServiceInstance() {
        return discoveryClient.getInstances(SERVICE_NAME)
                .stream()
                .findFirst()
                .orElseThrow(() -> new InnerException(String.format("Сервис %s недоступен.", SERVICE_NAME)));
    }
}
