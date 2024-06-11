package org.example.customerservice.mapper;

import org.example.customerservice.entity.CurrentRequestStatus;
import org.example.starter.dto.CurrentRequestStatusDto;

import java.util.List;
import java.util.stream.Collectors;

public class CurrentRequestStatusMapper {

    public static CurrentRequestStatusDto toDto(CurrentRequestStatus currentRequestStatus) {
        return new CurrentRequestStatusDto(
                currentRequestStatus.getRequest().getId(),
                currentRequestStatus.getRequestStatus().getId(),
                currentRequestStatus.getChangeDatetime()
        );
    }

    public static List<CurrentRequestStatusDto> toDto(List<CurrentRequestStatus> currentRequestStatuses) {
        return currentRequestStatuses.stream()
                .map(CurrentRequestStatusMapper::toDto)
                .collect(Collectors.toList());
    }
}
