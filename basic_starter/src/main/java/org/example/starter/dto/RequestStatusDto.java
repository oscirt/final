package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект передачи данных статуса запроса на открытие депозита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStatusDto {

    /**
     * Идентификатор статуса запроса
     */
    private Integer id;

    /**
     * Название статуса запроса
     */
    private String name;
}
