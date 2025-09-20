package com.example.demo.mapper;

import com.example.demo.domain.Customer;
import com.example.demo.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
    default Customer fromId(String id) {
        if (id == null) {
            return null;
        }
        Customer account = new Customer();
        account.setId(id);
        return account;
    }
}

