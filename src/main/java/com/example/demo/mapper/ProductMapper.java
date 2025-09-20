package com.example.demo.mapper;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    default Product fromId(String id) {
        if (id == null) {
            return null;
        }
        Product account = new Product();
        account.setId(id);
        return account;
    }
}
