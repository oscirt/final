package org.example.deposit.repository;

import org.example.deposit.entity.DepositPercentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositPercentTypeRepository extends JpaRepository<DepositPercentType, Integer> {
}
