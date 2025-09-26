package com.example.demo.service;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.Login;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {
    AccountDTO save(AccountDTO accountDTO);

    String checkLogin(Login login);


    Page<AccountDTO> findAll(Pageable pageable);


    Optional<AccountDTO> findOne(String id);

    void delete(String id);

}
