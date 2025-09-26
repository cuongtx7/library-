package com.example.demo.service.impl;

import com.example.demo.domain.Product;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Optional<CategoryDTO> category = null;
        try {
            category = categoryService.findOne(productDTO.getIdCategory());
        } catch (Exception e) {
            throw new RuntimeException("không lấy được thông tin category");
        }
        if (!category.isPresent()) {
            throw new RuntimeException(productDTO.getIdCategory() + " không có thông tin category");
        }

        LocalDateTime now = LocalDateTime.now();

        if (productDTO.getId() != null) {
            productDTO.setLastModifiedBy(null);
            productDTO.setLastModifiedDate(now);
        } else {
            String newId = generateNewId();
            productDTO.setId(newId);
            productDTO.setCreatedBy(null);
            productDTO.setCreatedDate(now);
        }
        Product customer = productMapper.toEntity(productDTO);
        customer = productRepository.save(customer);
        return productMapper.toDto(customer);

    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Override
    public Optional<ProductDTO> findOne(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDto);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    private String generateNewId() {
        List<Product> list = productRepository.findTopByIdOrderByIdDesc();
        if (list.isEmpty()) {
            return "PD001";
        } else {
            String lastId = list.get(0).getId();
            int num = Integer.parseInt(lastId.substring(2));
            num++;
            return String.format("PD%03d", num);
        }
    }
}
