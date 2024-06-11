package org.example.deposit.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.deposit.repository.DepositRepository;
import org.example.deposit.service.DepositService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;
}
