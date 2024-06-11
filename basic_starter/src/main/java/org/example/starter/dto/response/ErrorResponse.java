package org.example.starter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Ответ, содержащий причину ошибки и статус код
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Причина ошибки
     */
    private String reason;

    /**
     * Статус код ошибки
     */
    private Integer code;
}
