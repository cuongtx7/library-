package com.example.demo.mapper;

import com.example.demo.domain.Author;
import com.example.demo.dto.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AuthorMapper extends EntityMapper<AuthorDTO, Author> {

    default Author fromId(String id) {
        if (id == null) {
            return null;
        }
        Author book = new Author();
        book.setId(id);
        return book;
    }
}
