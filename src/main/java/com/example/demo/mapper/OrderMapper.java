package com.example.demo.mapper;

import com.example.demo.domain.OrderTable;
import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductVariantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, OrderTable> {

    OrderTable toEntity(OrderDTO dto);

    OrderDTO toDto(OrderTable entity);

}

