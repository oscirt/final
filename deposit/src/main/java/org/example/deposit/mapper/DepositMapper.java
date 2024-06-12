package org.example.deposit.mapper;

import org.example.deposit.entity.Deposit;
import org.example.deposit.entity.DepositPercentType;
import org.example.deposit.entity.DepositType;
import org.example.starter.dto.DepositDto;

import java.util.List;
import java.util.stream.Collectors;

public class DepositMapper {

    public static Deposit toEntity(
            DepositDto dto,
            Long customerId,
            DepositType depositType,
            DepositPercentType percentType) {
        return new Deposit(
                dto.getId(),
                customerId,
                dto.getDepositAccountId(),
                depositType,
                dto.getIsDepositRefill(),
                dto.getDepositAmount(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getDepositRate(),
                percentType,
                dto.getPercentPaymentAccountId(),
                dto.getPercentPaymentDate(),
                dto.getIsCapitalization(),
                dto.getRefundAccountId()
        );
    }

    public static DepositDto toDto(Deposit deposit) {
        return new DepositDto(
                deposit.getId(),
                deposit.getCustomerId(),
                deposit.getDepositAccountId(),
                deposit.getDepositType().getId(),
                deposit.getIsDepositRefill(),
                deposit.getDepositAmount(),
                deposit.getStartDate(),
                deposit.getEndDate(),
                deposit.getDepositRate(),
                deposit.getDepositPercentType().getId(),
                deposit.getPercentPaymentDate(),
                deposit.getPercentPaymentAccountId(),
                deposit.getCapitalization(),
                deposit.getRefundAccountId()
        );
    }

    public static List<DepositDto> toDto(List<Deposit> deposits) {
        return deposits.stream()
                .map(DepositMapper::toDto)
                .collect(Collectors.toList());
    }
}
