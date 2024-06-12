package org.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Объект данных для статуса запроса
 */
@Entity
@Table(name = "request_statuses", schema = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStatus {

    /**
     * Идентификатор статуса запроса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request_status")
    private Integer id;

    /**
     * Название статуса запроса
     */
    @Column(name = "request_status_name", nullable = false)
    private String name;
}
