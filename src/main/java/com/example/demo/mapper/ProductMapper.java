package com.example.demo.mapper;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Named("toDTO")
    ProductDTO toDTO(Product product);

    @Named("toEntity")
    Product toEntity(ProductDTO productDTO);
}
