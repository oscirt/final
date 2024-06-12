package org.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.customerservice.entity.id.CurrentRequestStatusId;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Объект данных текущего статуса заявки
 */
@Entity
@Table(name = "current_request_status", schema = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentRequestStatus {

    /**
     * Идентификатор текущего статуса
     */
    @EmbeddedId
    private CurrentRequestStatusId currentRequestStatusId;

    /**
     * Время изменения статуса запроса
     */
    @Column(name = "change_datetime")
    private LocalDateTime changeDatetime;
}
