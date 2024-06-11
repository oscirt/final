package org.example.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Настройки для jwt токена из файла конфигурации
 */
@ConfigurationProperties(value = "jwt")
@Data
public class JwtTokenProperties {

    private String secret;
    private Long expirationMs;
}
