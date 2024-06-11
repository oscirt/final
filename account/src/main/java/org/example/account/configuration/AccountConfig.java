package org.example.account.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Базовая конфигурация для сервиса счетов
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.example.account.repository")
@EntityScan(basePackages = "org.example.account.entity")
@EnableTransactionManagement
public class AccountConfig {
}
