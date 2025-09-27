package com.example.demo.mapper;

import com.example.demo.domain.OrderDetail;
import com.example.demo.dto.OrderDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrderDetailMapper extends EntityMapper<OrderDetailDTO, OrderDetail>{

}