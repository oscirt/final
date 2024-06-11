package org.example.deposit.entity.id;

import org.example.deposit.entity.Deposit;

import java.io.Serializable;

public class CustomerDepositsId implements Serializable {

    private Long customerId;
    private Deposit deposit;
}
