package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект передачи данных вида депозита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositTypeDto {

    /**
     * Идентификатор вида депозита
     */
    private Integer id;

    /**
     * Название вида депозита
     */
    private String name;
}
