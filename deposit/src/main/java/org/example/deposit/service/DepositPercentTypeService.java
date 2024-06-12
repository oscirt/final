package org.example.deposit.service;

import org.example.deposit.entity.DepositPercentType;

import java.util.List;

public interface DepositPercentTypeService {

    DepositPercentType getDepositPercentType(Integer depositPercentTypeId);
    List<DepositPercentType> getAllDepositPercentTypes();
}
