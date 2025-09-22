package com.example.demo.services;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.responses.ConfigResponse;

public interface CategoryService {
    ConfigResponse<CategoryDTO> getCategories(Integer page, Integer size);
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
    CategoryDTO delete(CategoryDTO categoryDTO);
}
