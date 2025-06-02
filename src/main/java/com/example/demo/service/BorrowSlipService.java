package com.example.demo.service;

import com.example.demo.dto.BorrowSlipDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BorrowSlipService {
    BorrowSlipDTO save(BorrowSlipDTO borrowSlipDTO);

    Page<BorrowSlipDTO> findAll(Pageable pageable);

    Optional<BorrowSlipDTO> findOne(String id);

    void delete(String id);
}
