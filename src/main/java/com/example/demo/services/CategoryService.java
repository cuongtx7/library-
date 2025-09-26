package com.example.demo.services;

import com.example.demo.domain.Category;
import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.Login;
import com.example.demo.responses.ConfigResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);

    Page<CategoryDTO> findAll(Pageable pageable);

    Optional<CategoryDTO> findOne(String id);

    void delete(String id);
}
