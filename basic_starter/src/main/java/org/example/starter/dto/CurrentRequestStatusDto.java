package org.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Объект передачи данных текущего статуса запроса
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentRequestStatusDto {

    /**
     * Идентификатор запроса
     */
    private Long requestId;

    /**
     * Идентификатор статуса запроса
     */
    private Integer requestStatusId;

    /**
     * Время изменения статуса
     */
    private LocalDateTime changeDatetime;
}
