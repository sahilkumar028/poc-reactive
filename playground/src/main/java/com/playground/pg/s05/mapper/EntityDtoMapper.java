package com.playground.pg.s05.mapper;

import com.playground.pg.s05.dto.CustomerDto;
import com.playground.pg.s05.entity.Customer;

public class  EntityDtoMapper {
    public static Customer toEntity(CustomerDto dto){
        return new Customer(
                dto.id(),
                dto.name(),
                dto.email());

    }
    public static CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail());
    }
}
