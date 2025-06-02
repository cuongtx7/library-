package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BookService {
    BookDTO save(BookDTO bookDTO);

    Page<BookDTO> findAll(Pageable pageable);

    Optional<BookDTO> findOne(String id);

    void delete(String id);


}
