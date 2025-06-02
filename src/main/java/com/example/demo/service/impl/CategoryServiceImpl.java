package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (categoryDTO.getId() == null ) {
            categoryDTO.setId(this.generateNextCategoryId());
            categoryDTO.setCreatedDate(Instant.now());
            Category category = categoryMapper.toEntity(categoryDTO);
            category = categoryRepository.save(category);
            return categoryMapper.toDto(category);
        } else {
            categoryDTO.setLastModifiedDate(Instant.now());
            Category category = categoryMapper.toEntity(categoryDTO);
            category = categoryRepository.save(category);
            return categoryMapper.toDto(category);
        }

    }

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
    }

    @Override
    public Optional<CategoryDTO> findOne(String id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }


    public String generateNextCategoryId() {
        String maxId = categoryRepository.findMaxCode();
        int nextNum = 1;

        if (maxId != null && maxId.startsWith("ca_")) {
            String numberPart = maxId.substring(3);
            try {
                nextNum = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
            }
        }

        return String.format("ca_%03d", nextNum);
    }
}
