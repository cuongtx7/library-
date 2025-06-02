package com.example.demo.service;

import com.example.demo.dto.LibrarianDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LibrarianService {
    LibrarianDTO save(LibrarianDTO librarianDTO);

    Page<LibrarianDTO> findAll(Pageable pageable);

    Optional<LibrarianDTO> findOne(String id);

    void delete(String id);
}
