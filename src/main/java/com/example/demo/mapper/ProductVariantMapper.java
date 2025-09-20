package com.example.demo.mapper;

import com.example.demo.domain.ProductVariant;
import com.example.demo.dto.ProductVariantDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVariantMapper extends EntityMapper<ProductVariantDTO, ProductVariant> {
    default ProductVariant fromId(String id) {
        if (id == null) {
            return null;
        }
        ProductVariant account = new ProductVariant();
        account.setId(id);
        return account;
    }
}
