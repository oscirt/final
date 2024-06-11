package org.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "request_statuses", schema = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request_status")
    private Integer id;

    @Column(name = "request_status_name", nullable = false)
    private String name;
}
