package org.example.customerservice.configuration;

import org.example.customerservice.security.AuthTokenFilter;
import org.example.customerservice.services.impl.CustomerServiceImpl;
import org.example.starter.jwt.BasicJwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурация Spring Security
 */
@Configuration
public class SecurityConfiguration {

    /**
     * Шифратора пароля BCrypt
     * @return BCrypt шифратор пароля
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Фильтр аутентификации
     * @param basicJwtUtils базовые функции для работы с JWT токеном
     * @param customerService сервис работы с клиентом
     * @return фильтр аутентификации
     */
    @Bean
    public AuthTokenFilter authenticationTokenFilter(BasicJwtUtils basicJwtUtils, CustomerServiceImpl customerService) {
        return new AuthTokenFilter(basicJwtUtils, customerService);
    }

    /**
     * Выбор способа хранения данных клиентов
     * @param customerService сервис клиентов
     * @return провайдер для доступа к данным клиентов
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomerServiceImpl customerService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(customerService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    /**
     * Менеджер аутентификации
     * @param configuration конфигурация аутентификации
     * @return менеджер аутентификации
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Цепочка фильтрации
     */
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity,
            CustomerServiceImpl customerService,
            BasicJwtUtils basicJwtUtils) throws Exception {
        return httpSecurity
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(request ->
                        request.antMatchers("/auth/**").permitAll()
                                .anyRequest().permitAll()
                )
                .authenticationProvider(authenticationProvider(customerService))
                .addFilterBefore(authenticationTokenFilter(basicJwtUtils, customerService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
