package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.TokenDTO;
import com.example.demo.requests.filters.CategoryFilters;
import com.example.demo.responses.ConfigResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    ConfigResponse<CategoryDTO> getCategories(Integer page, Integer size, CategoryFilters filters);
    CategoryDTO save(CategoryDTO categoryDTO, TokenDTO tokenDTO);
    CategoryDTO update(CategoryDTO categoryDTO, TokenDTO tokenDTO);
    CategoryDTO getCategory(String id);
    CategoryDTO delete(String id, TokenDTO tokenDTO);
}
