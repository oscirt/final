package org.example.deposit.repository;

import org.example.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {


    @Query("select d from Deposit d join fetch d.depositType join fetch d.depositPercentType")
    Optional<Deposit> findById(Integer id);
}
