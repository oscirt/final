package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Объект передачи данных запроса на открытие депозита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    /**
     * Идентификатор запроса
     */
    private Long id;

    /**
     * Идентификатор клиента
     */
    private Long customerId;

    /**
     * Дата создания запроса
     */
    private LocalDate date;

    /**
     * Идентификатор депозита
     */
    private Integer depositId;
}
