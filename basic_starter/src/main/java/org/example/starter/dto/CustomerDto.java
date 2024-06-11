package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект передачи данных клиента
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    /**
     * Идентификатор клиента
     */
    private Long id;

    /**
     * Идентификатор счета клиента
     */
    private Integer bankAccountId;

    /**
     * Номер телефона клиента
     */
    private String phoneNumber;
}
