package org.example.deposit.service;

import org.example.deposit.entity.DepositType;

import java.util.List;

public interface DepositTypeService {

    DepositType getDepositType(Integer depositTypeId);
    List<DepositType> getAllDepositTypes();
}
