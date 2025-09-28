package com.example.demo.mapper;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

}
