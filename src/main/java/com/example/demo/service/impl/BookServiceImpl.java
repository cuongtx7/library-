package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public BookDTO save(BookDTO bookDTO) {
        Optional<CategoryDTO> categoryDTO = categoryService.findOne(bookDTO.getCategoryId());
        if (categoryDTO.isPresent()) {
            throw new RuntimeException("categoriesId không có categories phù hợp");
        }
        if (bookDTO.getId() == null ) {
            bookDTO.setId(this.generateNextCategoryId());
            bookDTO.setCreatedDate(Instant.now());
            Book book = bookMapper.toEntity(bookDTO);
            book = bookRepository.save(book);
            return bookMapper.toDto(book);
        } else {
            bookDTO.setLastModifiedDate(Instant.now());
            Book book = bookMapper.toEntity(bookDTO);
            book = bookRepository.save(book);
            return bookMapper.toDto(book);
        }

    }

    @Override
    public Page<BookDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toDto);
    }

    @Override
    public Optional<BookDTO> findOne(String id) {
        return bookRepository.findById(id).map(bookMapper::toDto);
    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    public String generateNextCategoryId() {
        String maxId = bookRepository.findMaxCode();
        int nextNum = 1;

        if (maxId != null && maxId.startsWith("B_")) {
            String numberPart = maxId.substring(3);
            try {
                nextNum = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
            }
        }

        return String.format("B_%03d", nextNum);
    }

}
