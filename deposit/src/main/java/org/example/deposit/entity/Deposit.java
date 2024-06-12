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
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "deposit_account_id", nullable = false)
    private Integer depositAccountId;

    @ManyToOne
    @JoinColumn(name = "deposit_type_id", nullable = false)
    private DepositType depositType;

    @Column(name = "deposit_refill", nullable = false)
    private Boolean isDepositRefill;

    @Column(name = "deposit_amount", nullable = false)
    private BigDecimal depositAmount;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "deposit_rate", nullable = false)
    private BigDecimal depositRate;

    @ManyToOne
    @JoinColumn(name = "type_percent_payment_id")
    private DepositPercentType depositPercentType;

    @Column(name = "percent_payment_account_id")
    private Integer percentPaymentAccountId;

    @Column(name = "percent_payment_date")
    private LocalDate percentPaymentDate;

    @Column(name = "capitalization")
    private Boolean capitalization;

    @Column(name = "deposit_refund_account_id")
    private Integer refundAccountId;
}
