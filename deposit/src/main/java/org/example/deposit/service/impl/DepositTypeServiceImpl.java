package org.example.deposit.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.deposit.repository.DepositTypeRepository;
import org.example.deposit.service.DepositTypeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositTypeServiceImpl implements DepositTypeService {

    private final DepositTypeRepository depositTypeRepository;
}
