package org.example.deposit.repository;

import org.example.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNullApi;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {


    @Query("select d from Deposit d join fetch d.depositType join fetch d.depositPercentType " +
            "where d.id=?1 and d.customerId=?2")
    Optional<Deposit> findDepositById(Long depositId, Long customerId);

    @Query("select d from Deposit d join d.depositType join fetch d.depositPercentType where d.customerId=?1")
    List<Deposit> findAllByCustomerId(Long customerId);
}
