package com.example.demo.mapper;

import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    default Order fromId(String id) {
        if (id == null) {
            return null;
        }
        Order account = new Order();
        account.setId(id);
        return account;
    }
}

