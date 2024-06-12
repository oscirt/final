package org.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Объект данных запроса на открытие депозита
 */
@Entity
@Table(name = "requests", schema = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    /**
     * Идентификатор запроса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request")
    private Long id;

    /**
     * Идентификатор клиента
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Дата отправки запроса
     */
    @Column(name = "request_date", nullable = false)
    private LocalDate date;

    /**
     * Идентификатор депозита
     */
    @Column(name = "deposits_id")
    private Long depositId;
}
