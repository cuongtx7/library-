package com.example.demo.service;

import com.example.demo.dto.LibrarianDTO;
import com.example.demo.dto.MenberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberService {
    MenberDTO save(MenberDTO menberDTO);

    Page<MenberDTO> findAll(Pageable pageable);

    Optional<MenberDTO> findOne(String id);

    void delete(String id);
}
