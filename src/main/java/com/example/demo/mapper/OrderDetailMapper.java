package com.example.demo.mapper;

import com.example.demo.domain.OrderDetail;
import com.example.demo.dto.OrderDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrderDetailMapper extends EntityMapper<OrderDetailDTO, OrderDetail> {
    default OrderDetail fromId(String id) {
        if (id == null) {
            return null;
        }
        OrderDetail account = new OrderDetail();
        account.setId(id);
        return account;
    }
}