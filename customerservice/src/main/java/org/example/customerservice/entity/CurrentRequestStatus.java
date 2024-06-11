package org.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.customerservice.entity.id.CurrentRequestStatusId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_request_status", schema = "customers")
@IdClass(CurrentRequestStatusId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentRequestStatus {

    @Id
    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Id
    @ManyToOne
    @JoinColumn(name = "request_status_id")
    private RequestStatus requestStatus;

    @Column(name = "change_datetime")
    private LocalDateTime changeDatetime;
}
