package org.example.customerservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.customerservice.repository.CustomerRepository;
import org.example.starter.exception.IntentionException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация интерфейса сервиса {@link UserDetailsService}
 * для управления клиентами
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    /**
     * Поиск данных о клиенте по его псевдониму
     * @param username псевдоним клиента
     * @return данные о клиенте
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        return customerRepository.findByUsername(username).orElseThrow(() -> new IntentionException(
                String.format("Пользователь с псевдонимом %s не найден.", username)
        ));
    }
}
