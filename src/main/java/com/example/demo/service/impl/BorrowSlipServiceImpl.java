package com.example.demo.service.impl;

import com.example.demo.domain.BorrowSlip;
import com.example.demo.dto.*;
import com.example.demo.mapper.BorrowSlipMapper;
import com.example.demo.repository.BorrowSlipRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.BorrowSlipService;
import com.example.demo.service.LibrarianService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowSlipServiceImpl implements BorrowSlipService {

    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private BorrowSlipMapper borrowSlipMapper;
    @Autowired
    private BorrowSlipRepository borrowSlipRepository;


    @Override
    public BorrowSlipDTO save(BorrowSlipDTO borrowSlipDTO) {
        Optional<BookDTO> bookDTO = bookService.findOne(borrowSlipDTO.getBookId());
        if (bookDTO.isPresent()) {
            throw new RuntimeException("bookId không có book phù hợp");
        }
        Optional<MenberDTO> menberDTO = memberService.findOne(borrowSlipDTO.getMemberId());

        if (menberDTO.isPresent()) {
            throw new RuntimeException("menberId không có menber phù hợp");
        }
        Optional<LibrarianDTO> librarianDTO = librarianService.findOne(borrowSlipDTO.getLibrarianId());

        if (librarianDTO.isPresent()) {
            throw new RuntimeException("librarianId không có Librarian phù hợp");
        }

        if (borrowSlipDTO.getId() == null) {
            borrowSlipDTO.setStatus("processing");
            borrowSlipDTO.setId(this.generateNextCategoryId());
            borrowSlipDTO.setBorrowDate(Instant.now());
            BorrowSlip borrowSlip = borrowSlipMapper.toEntity(borrowSlipDTO);
            borrowSlip.setCreatedDate(Instant.now());
            borrowSlip = borrowSlipRepository.save(borrowSlip);
            return borrowSlipMapper.toDto(borrowSlip);
        } else {
            BorrowSlip borrowSlip = borrowSlipMapper.toEntity(borrowSlipDTO);
            borrowSlip.setLastModifiedDate(Instant.now());
            borrowSlip = borrowSlipRepository.save(borrowSlip);
            return borrowSlipMapper.toDto(borrowSlip);
        }

    }

    @Override
    public Page<BorrowSlipDTO> findAll(Pageable pageable) {
        return borrowSlipRepository.findAll(pageable).map(borrowSlipMapper::toDto);
    }

    @Override
    public Optional<BorrowSlipDTO> findOne(String id) {
        return borrowSlipRepository.findById(id).map(borrowSlipMapper::toDto);
    }

    @Override
    public void delete(String id) {
        borrowSlipRepository.deleteById(id);
    }


    public String generateNextCategoryId() {
        String maxId = borrowSlipRepository.findMaxCode();
        int nextNum = 1;

        if (maxId != null && maxId.startsWith("Slip_")) {
            String numberPart = maxId.substring(3);
            try {
                nextNum = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
            }
        }

        return String.format("Slip_%03d", nextNum);
    }
}
