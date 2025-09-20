package com.example.demo.mapper;
import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
    default Category fromId(String id) {
        if (id == null) {
            return null;
        }
        Category account = new Category();
        account.setId(id);
        return account;
    }
}
