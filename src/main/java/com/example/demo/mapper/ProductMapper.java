package com.example.demo.mapper;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "idCategory", target = "category.id")
    Product toEntity(ProductDTO dto);

    @Mapping(source = "category.id", target = "idCategory")
    ProductDTO toDto(Product entity);

    default Product fromId(String id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
