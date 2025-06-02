package com.example.demo.mapper;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.MenberDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface MenberMapper extends EntityMapper<MenberDTO, Member> {

    default Member fromId(String id) {
        if (id == null) {
            return null;
        }
        Member book = new Member();
        book.setId(id);
        return book;
    }
}
