package org.example.starter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Конфигурация для базового стартера
 */
@Configuration
@ComponentScan("org.example.starter")
@EnableConfigurationProperties(JwtTokenProperties.class)
public class StarterAutoConfiguration {

    /**
     * Создание бина клиента {@link RestTemplate}
     * @return клиент {@link RestTemplate}
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
