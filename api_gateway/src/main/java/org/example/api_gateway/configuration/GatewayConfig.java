package org.example.api_gateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация маршрутов шлюза
 */
@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    /**
     * Определяет основные маршруты для шлюза
     * @param builder билдер для маршрутизации
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("customerservice", r -> r
                        .path("/auth/**", "/customer/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://customerservice"))
                .route("accounts", r -> r
                        .path("/account/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://accounts"))
                .build();
    }
}
