package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.SeachOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {
    OrderDTO save(OrderDTO customerDTO);

    Page<OrderDTO> findAll(SeachOrder accouId , Pageable pageable);

    Optional<OrderDTO> findOne(String id);

    void delete(String id);
}
