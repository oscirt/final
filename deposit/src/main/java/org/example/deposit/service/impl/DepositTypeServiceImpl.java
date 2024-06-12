package org.example.deposit.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.deposit.entity.DepositType;
import org.example.deposit.repository.DepositTypeRepository;
import org.example.deposit.service.DepositTypeService;
import org.example.starter.exception.IntentionException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositTypeServiceImpl implements DepositTypeService {

    private final DepositTypeRepository depositTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public DepositType getDepositType(Integer depositTypeId) {
        return depositTypeRepository.findById(depositTypeId)
                .orElseThrow(() -> new IntentionException(String.format(
                        "Тип депозита с id %d не найден.",
                        depositTypeId
                )));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepositType> getAllDepositTypes() {
        return depositTypeRepository.findAll();
    }
}
