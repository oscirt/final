package org.example.deposit.mapper;

import org.example.deposit.entity.DepositPercentType;
import org.example.starter.dto.DepositPercentTypeDto;

import java.util.List;
import java.util.stream.Collectors;

public class DepositPercentTypeMapper {

    public static DepositPercentTypeDto toDto(DepositPercentType type) {
        return new DepositPercentTypeDto(
                type.getId(),
                type.getName()
        );
    }

    public static List<DepositPercentTypeDto> toDto(List<DepositPercentType> types) {
        return types.stream()
                .map(DepositPercentTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
