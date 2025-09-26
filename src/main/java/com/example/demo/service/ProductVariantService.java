package com.example.demo.service;

import com.example.demo.dto.ProductVariantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductVariantService {
    ProductVariantDTO save(ProductVariantDTO customerDTO);

    Page<ProductVariantDTO> findAll(Pageable pageable);

    Optional<ProductVariantDTO> findOne(String id);

    void delete(String id);
}
