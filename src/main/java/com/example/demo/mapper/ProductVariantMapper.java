package com.example.demo.mapper;

import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.ProductVariantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVariantMapper extends EntityMapper<ProductVariantDTO, ProductVariant> {


    ProductVariant toEntity(ProductVariantDTO dto);


    ProductVariantDTO toDto(ProductVariant entity);

}
