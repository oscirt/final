package org.example.customerservice.mapper;

import org.example.customerservice.entity.RequestStatus;
import org.example.starter.dto.RequestStatusDto;

import java.util.List;
import java.util.stream.Collectors;

public class RequestStatusMapper {

    public static RequestStatusDto toDto(RequestStatus requestStatus) {
        return new RequestStatusDto(
                requestStatus.getId(),
                requestStatus.getName()
        );
    }

    public static List<RequestStatusDto> toDto(List<RequestStatus> requestStatuses) {
        return requestStatuses.stream()
                .map(RequestStatusMapper::toDto)
                .collect(Collectors.toList());
    }
}
