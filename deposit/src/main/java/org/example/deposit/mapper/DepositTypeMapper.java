package org.example.deposit.mapper;

import org.example.deposit.entity.DepositType;
import org.example.starter.dto.DepositTypeDto;

import java.util.List;
import java.util.stream.Collectors;

public class DepositTypeMapper {

    public static DepositTypeDto toDto(DepositType type) {
        return new DepositTypeDto(
                type.getId(),
                type.getName()
        );
    }

    public static List<DepositTypeDto> toDto(List<DepositType> types) {
        return types.stream()
                .map(DepositTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
