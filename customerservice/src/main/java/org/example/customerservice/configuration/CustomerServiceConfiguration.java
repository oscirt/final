package org.example.customerservice.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.customerservice.repository")
@EntityScan(basePackages = "org.example.customerservice.entity")
@EnableTransactionManagement
public class CustomerServiceConfiguration {
}
