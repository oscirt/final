package org.example.deposit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "types_percent_payment", schema = "deposits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositPercentType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_type_percent_payment")
    private Integer id;

    @Column(name = "type_percent_payment_period", nullable = false)
    private String name;
}
