package org.example.api_gateway.configuration;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * Определяет валидность маршрутизации
 */
@Component
public class RouterValidator {

    /**
     * Открытые эндпоинты, к которым не будет применяться фильтрация
     */
    public static final List<String> openEndpoints = List.of("/login");

    /**
     * Проверка отсутствия адреса запроса в открытых эндпоинтах
     */
    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
