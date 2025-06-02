package com.example.demo.mapper;


import com.example.demo.domain.BorrowSlip;
import com.example.demo.dto.BorrowSlipDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface BorrowSlipMapper extends EntityMapper<BorrowSlipDTO, BorrowSlip> {

    default BorrowSlip fromId(String id) {
        if (id == null) {
            return null;
        }
        BorrowSlip book = new BorrowSlip();
        book.setId(id);
        return book;
    }
}
