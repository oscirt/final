package org.example.deposit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.deposit.entity.id.CustomerDepositsId;

import javax.persistence.*;

@Entity
@Table(name = "customers_deposits", schema = "deposits")
@IdClass(CustomerDepositsId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDeposits {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @ManyToOne
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;
}
