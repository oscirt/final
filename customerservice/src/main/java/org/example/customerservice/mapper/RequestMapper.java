package org.example.customerservice.mapper;

import org.example.customerservice.entity.Customer;
import org.example.customerservice.entity.Request;
import org.example.starter.dto.RequestDto;

import java.util.List;
import java.util.stream.Collectors;

public class RequestMapper {

    public static Request toEntity(RequestDto dto, Customer customer) {
        return new Request(
                dto.getId(),
                customer,
                dto.getDate(),
                dto.getDepositId()
        );
    }

    public static RequestDto toDto(Request request) {
        return new RequestDto(
                request.getId(),
                request.getCustomer().getId(),
                request.getDate(),
                request.getDepositId()
        );
    }

    public static List<RequestDto> toDto(List<Request> requests) {
        return requests.stream()
                .map(RequestMapper::toDto)
                .collect(Collectors.toList());
    }
}
