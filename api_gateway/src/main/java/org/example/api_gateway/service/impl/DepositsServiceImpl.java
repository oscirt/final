package org.example.api_gateway.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.api_gateway.entity.request.DepositOptionsRequest;
import org.example.api_gateway.entity.response.DepositOptionsResponse;
import org.example.api_gateway.service.DepositsService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class DepositsServiceImpl implements DepositsService {

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

        BigDecimal monthlyRate = BigDecimal.valueOf(395).divide(BigDecimal.valueOf(365), RoundingMode.CEILING);
        if (request.getIsCapitalization()) {
            for (int i = 0; i < request.getDepositMonths(); i++) {
                resultAmount = resultAmount.multiply(percentRate).multiply(monthlyRate);
            }
        } else {
            resultAmount = resultAmount.multiply(percentRate.divide(BigDecimal.valueOf(4), RoundingMode.CEILING));
        }

        return new DepositOptionsResponse(resultAmount, percentRate);
    }
}
