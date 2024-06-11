package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Объект данных депозита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDto {

    /**
     * Идентификатор депозита
     */
    private Integer id;

    /**
     * Идентификатор счета вклада
     */
    private Integer depositAccountId;

    /**
     * Идентификатор вида вклада
     */
    private Integer depositTypeId;

    /**
     * Вклад пополняемый
     */
    private Boolean isDepositRefill;

    /**
     * Сумма на счете вклада
     */
    private BigDecimal depositAmount;

    /**
     * Дата открытия вклада
     */
    private LocalDate startDate;

    /**
     * Дата окончания существования вклада по условию
     */
    private LocalDate endDate;

    /**
     * Процентная ставка
     */
    private BigDecimal depositRate;

    /**
     * Период выплат
     */
    private Integer depositPercentTypeId;

    /**
     * Дата выплаты процентов
     */
    private LocalDate percentPaymentDate;

    /**
     * Счет выплат
     */
    private Integer percentPaymentAccountId;

    /**
     * Капитализация процентов по вкладу
     */
    private Boolean isCapitalization;

    /**
     * Счет возвращения вклада
     */
    private Integer deposit_refund_account_id;
}
