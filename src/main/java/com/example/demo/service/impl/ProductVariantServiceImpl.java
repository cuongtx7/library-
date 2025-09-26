package com.example.demo.service.impl;

import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductVariantDTO;
import com.example.demo.mapper.ProductVariantMapper;
import com.example.demo.repository.ProductVariantRepository;
import com.example.demo.service.ProductVariantService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {

    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private ProductVariantMapper productMapper;

    @Autowired
    private ProductService productService;

    @Override
    public ProductVariantDTO save(ProductVariantDTO customerDTO) {
        Optional<ProductDTO> category = null;
        try {
            category = productService.findOne(customerDTO.getProductId());
        } catch (Exception e) {
            throw new RuntimeException("không lấy được thông tin ProductVariant");
        }
        if (!category.isPresent()) {
            throw new RuntimeException(customerDTO.getProductId() + " không có thông tin ProductVariant");
        }
        LocalDateTime now = LocalDateTime.now();
        if (customerDTO.getId() != null) {
            if (customerDTO.getDiscount() == null || customerDTO.getDiscount().equals("")) {
                customerDTO.setDiscount((float) 0);
            }
            customerDTO.setLastModifiedBy(null);
            customerDTO.setLastModifiedDate(now);
        } else {
            String newId = generateNewId();
            if (customerDTO.getDiscount() == null || customerDTO.getDiscount().equals("")) {
                customerDTO.setDiscount((float) 0);
            }
            customerDTO.setId(newId);
            customerDTO.setCreatedBy(null);
            customerDTO.setCreatedDate(now);
        }
        ProductVariant customer = productMapper.toEntity(customerDTO);
        customer = productVariantRepository.save(customer);
        return productMapper.toDto(customer);
    }

    @Override
    public Page<ProductVariantDTO> findAll(Pageable pageable) {
        return productVariantRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Override
    public Optional<ProductVariantDTO> findOne(String id) {
        return productVariantRepository.findById(id)
                .map(productMapper::toDto);
    }

    @Override
    public void delete(String id) {
        productVariantRepository.deleteById(id);
    }

    private String generateNewId() {
        List<ProductVariant> list = productVariantRepository.findTopByIdOrderByIdDesc();
        if (list.isEmpty()) {
            return "PDV001";
        } else {
            String lastId = list.get(0).getId();
            int num = Integer.parseInt(lastId.substring(3));
            num++;
            return String.format("PDV%03d", num);
        }
    }
}
