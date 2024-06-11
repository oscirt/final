package org.example.api_gateway.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.starter.jwt.BasicJwtUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Фильтр запросов, который фильтрует запросы на наличие
 * Authorization хедера и правильности токена
 */
@RefreshScope
@Component
@RequiredArgsConstructor
@Log4j2
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator validator;
    private final BasicJwtUtils basicJwtUtils;

    /**
     * Метод фильтрации запросов
     * @param exchange запрос-ответ
     * @param chain цепочка фильтрации
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (validator.isSecured.test(request)) {
            log.info("Проверка хедера на наличие Authorization");
            if (!request.getHeaders().containsKey("Authorization")) {
                return onError(exchange, HttpStatus.FORBIDDEN);
            }

            log.info("Получение JWT токена из хедера");
            String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            if (!basicJwtUtils.validateJwtToken(token)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            log.info("Валидация прошла успешно");
        }

        return chain.filter(exchange);
    }

    /**
     * Возвращает ответ при ошибке
     * @param exchange запрос-ответ
     * @param httpStatus статус ответа
     */
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
