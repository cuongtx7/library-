package com.example.demo.service.impl;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.TokenDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.requests.filters.CategoryFilters;
import com.example.demo.responses.ConfigResponse;
import com.example.demo.service.CategoryService;
import com.example.demo.specification.CategorySpecification;
import com.example.demo.until.CheckNumber;
import com.example.demo.until.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GenerateId generateId;

    @Override
    public ConfigResponse<CategoryDTO> getCategories(Integer page, Integer size, CategoryFilters filters) {
        try {
            page = CheckNumber.isNumeric(String.valueOf(page)) ? page : 1;
            size = CheckNumber.isNumeric(String.valueOf(size)) ? size : 14;

            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdDate"));

            Specification<Category> spec = CategorySpecification.filters(filters);
            Page<Category> categories = categoryRepository.findAll(spec, pageable);

            List<CategoryDTO> categoryDTOS = categories.getContent()
                    .stream()
                    .map(categoryMapper::toDto)
                    .collect(Collectors.toList());

            return new ConfigResponse<>(categoryDTOS, (int) categories.getTotalElements());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO, TokenDTO tokenDTO) {
        try {
            Category category = categoryMapper.toEntity(categoryDTO);
            category.setId(generateId.generateId(categoryRepository,"CT"));
            category.setCreatedBy(tokenDTO.getFullname());
            category.setCreatedDate(LocalDateTime.now());
            category.setLastModifiedDate(LocalDateTime.now());
            category.setLastModifiedBy(tokenDTO.getFullname());
            category.setIsDelete(false);

            return categoryMapper.toDto(categoryRepository.save(category));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, TokenDTO tokenDTO) {
        try {
            Category category = categoryRepository.findById(categoryDTO.getId())
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy thể loại cho id: "+ categoryDTO.getId()));

            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setLastModifiedDate(LocalDateTime.now());
            category.setLastModifiedBy(tokenDTO.getFullname());

            return categoryMapper.toDto(categoryRepository.save(category));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryDTO getCategory(String id) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy thể loại cho id: " + id));

            return categoryMapper.toDto(category);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public CategoryDTO delete(String id, TokenDTO tokenDTO) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy thể loại cho id: " + id));

            category.setIsDelete(true);
            category.setLastModifiedDate(LocalDateTime.now());
            category.setLastModifiedBy(tokenDTO.getFullname());

            return categoryMapper.toDto(categoryRepository.save(category));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

