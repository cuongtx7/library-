package com.example.demo.service;

;
import com.example.demo.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);

    Page<CategoryDTO> findAll(Pageable pageable);

    Optional<CategoryDTO> findOne(String id);

    void delete(String id);
}
