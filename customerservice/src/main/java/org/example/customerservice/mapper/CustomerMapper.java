package org.example.customerservice.mapper;

import org.example.customerservice.entity.Customer;
import org.example.starter.dto.CustomerDto;

public class CustomerMapper {

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getBankAccountId(),
                customer.getPhoneNumber()
        );
    }
}
