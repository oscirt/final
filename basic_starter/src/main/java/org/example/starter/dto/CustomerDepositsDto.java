package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект передачи данных связей клиентов и депозитов
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDepositsDto {

    /**
     * Идентификатор клиента
     */
    private Long customerId;

    /**
     * Идентификатор депозита
     */
    private Long depositId;
}
