package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Объект передачи данных счета
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    /**
     * Идентификатор счета
     */
    private Integer id;

    /**
     * Номер счета
     */
    private String accountNumber;

    /**
     * Сумма денег на счету
     */
    private BigDecimal amount;
}
