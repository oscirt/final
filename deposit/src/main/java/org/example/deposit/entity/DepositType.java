package org.example.deposit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "deposits_types", schema = "deposits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_deposit_types")
    private Integer id;

    @Column(name = "deposits_types_name", nullable = false)
    private String name;
}
