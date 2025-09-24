package com.example.demo.service;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.Login;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);

    Page<CustomerDTO> findAll(Pageable pageable);

    Optional<CustomerDTO> findOne(String id);

    void delete(String id);
}
