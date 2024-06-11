package org.example.account.mapper;

import org.example.starter.dto.AccountDto;
import org.example.account.entity.Account;
import org.springframework.stereotype.Component;

/**
 * Маппер для преобразования {@link Account} в {@link AccountDto}
 */
@Component
public class AccountMapper {

    /**
     * Преобразует {@link Account} в {@link AccountDto}
     * @param account объект счета клиента
     * @return объект передачи данных счета клиента
     */
    public static AccountDto toDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getAmount()
        );
    }
}
