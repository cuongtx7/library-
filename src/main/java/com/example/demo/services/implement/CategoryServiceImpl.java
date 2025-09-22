package com.example.demo.services.implement;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.exceptions.DuplicateRecordException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.responses.ConfigResponse;
import com.example.demo.services.CategoryService;
import com.example.demo.until.CheckNumber;
import com.example.demo.until.GenerateId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    GenerateId generateId;

    @Override
    public ConfigResponse<CategoryDTO> getCategories(Integer page, Integer size) {
        try {
            page = CheckNumber.isNumeric(String.valueOf(page)) ? page : 1;
            size = CheckNumber.isNumeric(String.valueOf(size)) ? size : 14;

            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));

            Page<Category> categories = categoryRepository.findAll(pageable);
            return new ConfigResponse<>(categoryMapper.toDTOList(categories.getContent()), (int) categories.getTotalElements());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("CategoryDTO cannot be null");
        }

        try {
            String newId = generateId.generateId(categoryRepository, "CT");
            if (categoryRepository.existsById(newId)) {
                throw new DuplicateRecordException("Duplicate ID generated: " + newId);
            }
            Category category = new Category();
//            category.setIdCategory(newId);
            category.setId(newId);
            category.setNameCategory(categoryDTO.getNameCategory());
            category.setDescriptionCategory(categoryDTO.getDescriptionCategory());

            Category savedCategory = categoryRepository.save(category);
            return categoryMapper.toDTO(savedCategory);
        } catch (IllegalArgumentException | DuplicateRecordException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to create category: " + e.getMessage(), e);
        }
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        try {
            Specification<Category> spec = Specification.where(
                    (root, query, cb) ->
                    cb.equal(root.get("idCategory"), categoryDTO.getIdCategory())
            );
            Category category = categoryRepository.findOne(spec)
                    .orElseThrow(() -> new NoSuchElementException("Category not found by id: " + categoryDTO.getIdCategory()));

            category.setNameCategory(categoryDTO.getNameCategory());
            category.setDescriptionCategory(categoryDTO.getNameCategory());

            return categoryMapper.toDTO(categoryRepository.save(category));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryDTO delete(CategoryDTO categoryDTO) {
        return null;
    }
}

