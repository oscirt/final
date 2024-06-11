package org.example.deposit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "deposits", schema = "deposits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_deposit")
    private Integer id;

    @Column(name = "deposit_account_id")
    private Integer depositAccountId;

    @ManyToOne
    private DepositType depositType;

    @Column(name = "deposit_refill")
    private Boolean isDepositRefill;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "deposit_rate")
    private BigDecimal depositRate;

    @ManyToOne
    private DepositPercentType depositPercentType;

    @Column(name = "percent_payment_date")
    private LocalDate percentPaymentDate;

    @Column(name = "percent_payment_account_id")
    private Integer percentPaymentAccountId;

    @Column(name = "capitalization")
    private Boolean capitalization;

    @Column(name = "deposit_refund_account_id")
    private Integer refundAccountId;
}
