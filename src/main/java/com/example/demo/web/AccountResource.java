package com.example.demo.web;

import com.example.demo.cors.JwtTokenProvider;
import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.Login;
import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.TokenSecurity;
import com.example.demo.requests.filters.AccountFilters;
import com.example.demo.responses.ConfigResponse;
import com.example.demo.service.AccountService;
import com.example.demo.until.JwtUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
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
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createAccount(
            @RequestBody AccountDTO accountDTO,
            @RequestHeader("Authorization") String authHeader
    )
    {
        String token = JwtUtil.getToken(authHeader);
        TokenDTO tokenDTO = jwtTokenProvider.getUserInfo(token);
        AccountDTO result = accountService.save(accountDTO, tokenDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<AccountDTO> updateAccount(
            @Valid @RequestBody AccountDTO accountDTO,
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = JwtUtil.getToken(authHeader);
        TokenDTO tokenDTO = jwtTokenProvider.getUserInfo(token);
        AccountDTO result = accountService.update(accountDTO, tokenDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenSecurity> login(@Valid @RequestBody Login login) throws URISyntaxException {
        AccountDTO result = accountService.checkLogin(login);
        if(result == null){
            return ResponseEntity.ok(new TokenSecurity());
        }
        String token = jwtTokenProvider.createToken(result.getUsername(),accountService.getAccountToken(result.getId()));
        return ResponseEntity.ok(new TokenSecurity(token));
    }

    @GetMapping("/account")
    public ResponseEntity<ConfigResponse<AccountDTO>> getAllAccount(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @ModelAttribute AccountFilters filters
            ) {
        return ResponseEntity.ok(accountService.getAccounts(page, size, filters));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> getAccount(
            @PathVariable String id
    ) {

        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable String id,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = JwtUtil.getToken(authHeader);
        TokenDTO tokenDTO = jwtTokenProvider.getUserInfo(token);
        accountService.delete(id, tokenDTO);
        return ResponseEntity.noContent().build();
    }
}
