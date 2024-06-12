package org.example.deposit.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("org.example.deposit.repository")
@EntityScan("org.example.deposit.entity")
@EnableTransactionManagement
public class DepositConfiguration {
}
