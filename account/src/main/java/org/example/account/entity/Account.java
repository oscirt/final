package org.example.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Сущность счета клиента
 */
@Entity
@Table(name = "bank_accounts", schema = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    /**
     * Идентификатор счета клиента
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_bank_account")
    private Integer id;

    /**
     * Номер счета клиента
     */
    @Column(name = "num_bank_account", nullable = false)
    private String accountNumber;

    /**
     * Сумма денег на счету клиента
     */
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}
