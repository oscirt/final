package org.example.deposit.service;

import org.example.deposit.entity.Deposit;

import java.util.List;

public interface DepositService {

    Deposit getDepositInfo(Long depositId, Long customerId);
    List<Deposit> getCustomerDeposits(Long customerId);
    Deposit createNewDeposit(Deposit deposit);
}
