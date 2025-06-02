package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AuthorService {

    AuthorDTO save(AuthorDTO authorDTO);

    Page<AuthorDTO> findAll(Pageable pageable);

    Optional<AuthorDTO> findOne(String id);
    void delete(String id);

}
