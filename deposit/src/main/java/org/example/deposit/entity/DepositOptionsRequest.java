package org.example.deposit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Опции депозита для расчета выплаты
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositOptionsRequest {

    /**
     * Идентификатор вида вклада
     */
    private Integer depositTypeId;

    /**
     * Сумма вклада в депозит
     */
    private BigDecimal depositAmount;

    /**
     * Период выплат
     */
    private Integer depositPercentTypeId;

    /**
     * Капитализация процентов по вкладу
     */
    private Boolean isCapitalization;

    /**
     * Срок депозита в месяцах
     */
    private Integer depositMonths;
}
