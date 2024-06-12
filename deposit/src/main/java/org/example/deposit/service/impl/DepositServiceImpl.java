package org.example.deposit.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.deposit.entity.Deposit;
import org.example.deposit.repository.DepositRepository;
import org.example.deposit.service.DepositService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;

    @Override
    @Transactional(readOnly = true)
    public Deposit getDepositInfo(Long depositId, Long customerId) {
        return depositRepository.findDepositById(depositId, customerId)
                .orElseThrow(() -> new IntentionException(String.format("Депозит с id %d не найден.", depositId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deposit> getCustomerDeposits(Long customerId) {
        return depositRepository.findAllByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Deposit createNewDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }
}
