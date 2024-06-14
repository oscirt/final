package org.example.deposit.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.deposit.entity.Deposit;
import org.example.deposit.entity.DepositOptionsRequest;
import org.example.deposit.entity.DepositOptionsResponse;
import org.example.deposit.repository.DepositRepository;
import org.example.deposit.service.DepositService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Override
    public DepositOptionsResponse calculate(DepositOptionsRequest request) {
        if (request.getDepositAmount().doubleValue() < 10_000) {
            throw new IntentionException("Сумма вклада меньше 10.000,00 руб.");
        }

        BigDecimal percentRate = BigDecimal.valueOf(1);
        BigDecimal resultAmount = request.getDepositAmount();

        switch (request.getDepositTypeId()) {
            case 1:
                percentRate = percentRate.add(BigDecimal.valueOf(0.049));
                break;
            case 2:
                percentRate = percentRate.add(BigDecimal.valueOf(0.067));
                break;
            case 3:
                percentRate = percentRate.add(BigDecimal.valueOf(0.09));
                break;
            default:
                throw new IntentionException(String.format("Вида вклада %d нет.", request.getDepositTypeId()));
        }

        BigDecimal monthlyRate = BigDecimal.valueOf(395).divide(BigDecimal.valueOf(365), 3, RoundingMode.CEILING);
        if (request.getIsCapitalization()) {
            for (int i = 0; i < request.getDepositMonths(); i++) {
                resultAmount = resultAmount.multiply(percentRate).multiply(monthlyRate);
            }
        } else {
            resultAmount = resultAmount.add(resultAmount.multiply(percentRate).subtract(resultAmount)
                    .divide(BigDecimal.valueOf(12f / request.getDepositMonths().doubleValue()), RoundingMode.CEILING));
        }

        return new DepositOptionsResponse(
                resultAmount.setScale(2, RoundingMode.CEILING),
                percentRate.setScale(2, RoundingMode.CEILING));
    }
}
