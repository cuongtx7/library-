package com.example.demo.service.impl;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {

        if (authorDTO.getId() == null) {
            authorDTO.setId(this.generateNextCategoryId());
            authorDTO.setCreatedDate(Instant.now());
            Author author = authorMapper.toEntity(authorDTO);
            author = authorRepository.save(author);
            return authorMapper.toDto(author);
        } else {
            authorDTO.setLastModifiedDate(Instant.now());
            Author author = authorMapper.toEntity(authorDTO);
            author = authorRepository.save(author);
            return authorMapper.toDto(author);
        }

    }

    @Override
    public Page<AuthorDTO> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable).map(authorMapper::toDto);
    }

    @Override
    public Optional<AuthorDTO> findOne(String id) {
        return authorRepository.findById(id).map(authorMapper::toDto);
    }

    @Override
    public void delete(String id) {
        authorRepository.deleteById(id);
    }

    public String generateNextCategoryId() {
        String maxId = authorRepository.findMaxCode();
        int nextNum = 1;

        if (maxId != null && maxId.startsWith("Au_")) {
            String numberPart = maxId.substring(3);
            try {
                nextNum = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
            }
        }

        return String.format("Au_%03d", nextNum);
    }
}
