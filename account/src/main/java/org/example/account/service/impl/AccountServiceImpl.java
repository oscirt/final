package org.example.account.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.account.entity.Account;
import org.example.account.repository.AccountRepository;
import org.example.account.service.AccountService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Реализация интерфейса сервиса {@link AccountService} для управления счетом
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * Пополняет счет аккаунта на нужную сумму
     *
     * @param accountId идентификатор счета аккаунта
     * @param amount сумма пополнения
     * @return объект счета после пополнения
     */
    @Override
    @Transactional
    public Account topUpAccount(Integer accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        BigDecimal currentAmount = account.getAmount();
        account.setAmount(currentAmount.add(amount));
        return accountRepository.save(account);
    }

    /**
     * Списывает со счета аккаунта нужную сумму
     *
     * @param accountId идентификатор счета аккаунта
     * @param amount сумма списания
     * @return объект счета после списания
     */
    @Override
    @Transactional
    public Account writeOffAccount(Integer accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);

        BigDecimal currentAmount = account.getAmount();
        BigDecimal subValue = currentAmount.subtract(amount);
        if (subValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IntentionException("Сумма вычета больше имеющейся на счету суммы.");
        }
        account.setAmount(subValue);

        return accountRepository.save(account);
    }

    /**
     * Возвращает объект счета по идентификатору счета
     *
     * @param id идентификатор счета
     * @return объект счета
     */
    @Override
    @Transactional(readOnly = true)
    public Account getAccountById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account with id %d not found.", id)));
    }
}
