package com.example.demo.service.impl;


import com.example.demo.domain.Librarian;
import com.example.demo.dto.LibrarianDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.LibrarianMapper;
import com.example.demo.repository.LibrarianRepository;
import com.example.demo.service.LibrarianService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private LibrarianMapper librarianMapper;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public LibrarianDTO save(LibrarianDTO librarianDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(librarianDTO.getEmail());
        userDTO.setPhone(librarianDTO.getPhoneNumber());
        userDTO.setUserName(librarianDTO.getUsername());
        List<Librarian> list = this.listLibrarian(userDTO);
        if (!list.isEmpty() || list != null) {
            Optional<LibrarianDTO> librarianDTO1 = this.findOne(librarianDTO.getId());
            if (librarianDTO1.isPresent()) {
                throw new RuntimeException("khong có dữ liệu");
            } else {
                librarianDTO.setLastModifiedDate(Instant.now());
                Librarian librarian = librarianMapper.toEntity(librarianDTO);
                librarian = librarianRepository.save(librarian);
                return librarianMapper.toDto(librarian);
            }
        } else {
            librarianDTO.setId(this.generateNextCategoryId());
            librarianDTO.setCreatedDate(Instant.now());
            Librarian librarian = librarianMapper.toEntity(librarianDTO);
            librarian = librarianRepository.save(librarian);
            return librarianMapper.toDto(librarian);
        }
    }

    @Override
    public Page<LibrarianDTO> findAll(Pageable pageable) {
        return librarianRepository.findAll(pageable).map(librarianMapper::toDto);
    }

    @Override
    public Optional<LibrarianDTO> findOne(String id) {
        return librarianRepository.findById(id).map(librarianMapper::toDto);
    }

    @Override
    public void delete(String id) {
        librarianRepository.deleteById(id);
    }


    public List<Librarian> listLibrarian(UserDTO userDTO) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT * FROM LIBRARIAN c WHERE 1=1 ");

        if (userDTO.getUserName() != null) {
            queryString.append(" AND c.user_name = :username");
        }
        if (userDTO.getEmail() != null) {
            queryString.append(" AND c.email = :email");
        }
        if (userDTO.getPhone() != null) {
            queryString.append(" AND c.phone_number = :phone");
        }

        Query query = entityManager.createNativeQuery(queryString.toString(), Librarian.class);

        if (userDTO.getUserName() != null) {
            query.setParameter("username", userDTO.getUserName());
        }
        if (userDTO.getEmail() != null) {
            query.setParameter("email", userDTO.getEmail());
        }
        if (userDTO.getPhone() != null) {
            query.setParameter("phone", userDTO.getPhone());
        }
        return query.getResultList();
    }

    public String generateNextCategoryId() {
        String maxId = librarianRepository.findMaxCode();
        int nextNum = 1;

        if (maxId != null && maxId.startsWith("La_")) {
            String numberPart = maxId.substring(3);
            try {
                nextNum = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
            }
        }

        return String.format("La_%03d", nextNum);
    }
}
