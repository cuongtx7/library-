package com.example.demo.mapper;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {


    Product toEntity(ProductDTO dto);

    ProductDTO toDto(Product entity);

}
