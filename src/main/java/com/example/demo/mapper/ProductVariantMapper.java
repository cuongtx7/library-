package com.example.demo.mapper;

import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.ProductVariantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVariantMapper extends EntityMapper<ProductVariantDTO, ProductVariant> {

    @Mapping(source = "productId", target = "product.id")
    ProductVariant toEntity(ProductVariantDTO dto);

    @Mapping(source = "product.id", target = "productId")
    ProductVariantDTO toDto(ProductVariant entity);

    default ProductVariant fromId(String id) {
        if (id == null) {
            return null;
        }
        ProductVariant product = new ProductVariant();
        product.setId(id);
        return product;
    }
}
