package com.example.demo.web;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.Login;
import com.example.demo.service.AccountService;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountResource {
    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO) throws URISyntaxException {

        if (accountDTO.getUsername() == null && accountDTO.getUsername().isEmpty()) {
            throw new RuntimeException("useName null");
        }
        if (accountDTO.getEmail() == null && accountDTO.getEmail().isEmpty()) {
            throw new RuntimeException("email null");
        }
        if (accountDTO.getPassword() == null && accountDTO.getPassword().isEmpty()) {
            throw new RuntimeException("password null");
        }

        AccountDTO result = accountService.save(accountDTO);
        return ResponseEntity.created(new URI("/api/account/" + result.getId())).body(result);
    }

    @PutMapping("/account")
    public ResponseEntity<AccountDTO> updateAccount(@Valid @RequestBody AccountDTO accountDTO) throws URISyntaxException {

        if (accountDTO.getUsername() == null || accountDTO.getUsername().isEmpty()) {
            throw new RuntimeException("Username null hoặc rỗng");
        }

        if (accountDTO.getEmail() == null || accountDTO.getEmail().isEmpty()) {
            throw new RuntimeException("Email null hoặc rỗng");
        }

        if (accountDTO.getPassword() == null || accountDTO.getPassword().isEmpty()) {
            throw new RuntimeException("Password null hoặc rỗng");
        }

        AccountDTO result = accountService.save(accountDTO);
        return ResponseEntity.created(new URI("/api/account/" + result.getId())).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@Valid @RequestBody Login login) throws URISyntaxException {

        if (login.getUserName() == null && login.getUserName().isEmpty()) {
            throw new RuntimeException("useName null");
        }
        if (login.getEmail() == null && login.getEmail().isEmpty()) {
            throw new RuntimeException("email null");
        }
        if (login.getPassword() == null && login.getPassword().isEmpty()) {
            throw new RuntimeException("password null");
        }

        Boolean result = accountService.checkLogin(login);
        return ResponseEntity.created(new URI("/api/account/" + login.getUserName())).body(result);
    }


    @GetMapping("/account")
    public ResponseEntity<List<AccountDTO>> getAllAccount(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {

        Page<AccountDTO> page = accountService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable String id) {
        Optional<AccountDTO> selectorDTO = accountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(selectorDTO);
    }

    @DeleteMapping("/selectors/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
