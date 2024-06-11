package org.example.account.repository;

import org.example.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для счета клиентов
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
