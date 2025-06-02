package com.example.demo.mapper;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface BookMapper extends EntityMapper<BookDTO, Book> {

    default Book fromId(String id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
