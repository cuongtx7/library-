package com.example.demo.mapper;

import com.example.demo.domain.Order;
import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductVariantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "productVariantId", target = "productVariant.id")
    Order toEntity(OrderDTO dto);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "productVariant.id", target = "productVariantId")
    OrderDTO toDto(Order entity);

    default Order fromId(String id) {
        if (id == null) {
            return null;
        }
        Order account = new Order();
        account.setId(id);
        return account;
    }
}

