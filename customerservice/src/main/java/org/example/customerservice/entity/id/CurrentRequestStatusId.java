package org.example.customerservice.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.customerservice.entity.Request;
import org.example.customerservice.entity.RequestStatus;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Идентификатор для статуса текущего запроса
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CurrentRequestStatusId implements Serializable {

    /**
     * Объект запроса
     */
    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    /**
     * Объект статуса запроса
     */
    @ManyToOne
    @JoinColumn(name = "request_status_id")
    private RequestStatus requestStatus;
}
