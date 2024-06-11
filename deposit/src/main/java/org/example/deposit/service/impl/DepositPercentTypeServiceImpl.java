package org.example.deposit.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.deposit.repository.DepositPercentTypeRepository;
import org.example.deposit.service.DepositPercentTypeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositPercentTypeServiceImpl implements DepositPercentTypeService {

    private DepositPercentTypeRepository depositPercentTypeRepository;
}
