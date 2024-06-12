package org.example.deposit.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.deposit.entity.DepositPercentType;
import org.example.deposit.repository.DepositPercentTypeRepository;
import org.example.deposit.service.DepositPercentTypeService;
import org.example.starter.exception.IntentionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositPercentTypeServiceImpl implements DepositPercentTypeService {

    private final DepositPercentTypeRepository depositPercentTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public DepositPercentType getDepositPercentType(Integer depositPercentTypeId) {
        return depositPercentTypeRepository.findById(depositPercentTypeId)
                .orElseThrow(() -> new IntentionException(String.format(
                        "Период выплаты с id %d не найден.",
                        depositPercentTypeId
                )));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepositPercentType> getAllDepositPercentTypes() {
        return depositPercentTypeRepository.findAll();
    }
}
