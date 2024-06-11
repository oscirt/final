package org.example.account.service;

import org.example.account.entity.Account;

import java.math.BigDecimal;

/**
 * Интерфейс сервиса для управления счетом
 */
public interface AccountService {

    /**
     * Пополняет счет аккаунта на нужную сумму
     * @param accountId идентификатор счета аккаунта
     * @param amount сумма пополнения
     * @return объект счета после пополнения
     */
    Account topUpAccount(Integer accountId, BigDecimal amount);

    /**
     * Списывает со счета аккаунта нужную сумму
     * @param accountId идентификатор счета аккаунта
     * @param amount сумма списания
     * @return объект счета после списания
     */
    Account writeOffAccount(Integer accountId, BigDecimal amount);

    /**
     * Возвращает объект счета по идентификатору счета
     * @param id идентификатор счета
     * @return объект счета
     */
    Account getAccountById(Integer id);
}
