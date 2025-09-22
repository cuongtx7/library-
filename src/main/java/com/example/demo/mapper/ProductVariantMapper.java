package com.example.demo.mapper;

import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.ProductVariantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVariantMapper {
   ProductVariantMapper INSTANCE = Mappers.getMapper(ProductVariantMapper.class);

   @Named("toDTO")
    ProductVariantDTO toDTO(ProductVariant productVariant);

   @Named("toEntity")
    ProductVariant toEntity(ProductVariantDTO productVariantDTO);
}
