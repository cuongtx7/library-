package com.example.demo.mapper;

import com.example.demo.domain.Librarian;
import com.example.demo.dto.LibrarianDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface LibrarianMapper extends EntityMapper<LibrarianDTO, Librarian> {

    default Librarian fromId(String id) {
        if (id == null) {
            return null;
        }
        Librarian book = new Librarian();
        book.setId(id);
        return book;
    }
}