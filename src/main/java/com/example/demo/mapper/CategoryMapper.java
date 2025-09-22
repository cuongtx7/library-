package com.example.demo.mapper;
import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper{
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Named("toDTO")
    CategoryDTO toDTO(Category category);

    @Named("toEntity")
    Category toEntity(CategoryDTO categoryDTO);

    @Named("toDTOList")
    @IterableMapping(qualifiedByName = "toDTO")
    List<CategoryDTO> toDTOList(List<Category> categoryList);
}
