package org.example.deposit.service;

import org.example.deposit.entity.Deposit;
import org.example.deposit.entity.DepositOptionsRequest;
import org.example.deposit.entity.DepositOptionsResponse;

import java.util.List;

public interface DepositService {

    Deposit getDepositInfo(Long depositId, Long customerId);
    List<Deposit> getCustomerDeposits(Long customerId);
    Deposit createNewDeposit(Deposit deposit);
    DepositOptionsResponse calculate(DepositOptionsRequest request);
}
