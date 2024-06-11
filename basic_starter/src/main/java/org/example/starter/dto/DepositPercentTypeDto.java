package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект передачи данных периода выплаты
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositPercentTypeDto {

    /**
     * Идентификатор периода выплаты
     */
    private Integer id;

    /**
     * Название периода выплаты
     */
    private String name;
}
