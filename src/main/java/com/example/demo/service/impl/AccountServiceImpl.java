package com.example.demo.service.impl;

import com.example.demo.domain.Account;
import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.Login;
import com.example.demo.dto.TokenDTO;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.requests.filters.AccountFilters;
import com.example.demo.responses.ConfigResponse;
import com.example.demo.service.AccountService;
import com.example.demo.specification.AccountSpecification;
import com.example.demo.until.CheckNumber;
import com.example.demo.until.GenerateId;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    GenerateId generateId;

    @Override
    public ConfigResponse<AccountDTO> getAccounts(Integer page, Integer size, AccountFilters filters) {
        try {
            page = CheckNumber.isNumeric(String.valueOf(page)) ? page : 1;
            size = CheckNumber.isNumeric(String.valueOf(size)) ? size : 14;

            Specification<Account> spec = AccountSpecification.filters(filters);
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdDate"));
            Page<Account> accounts = accountRepository.findAll(spec, pageable);

            List<AccountDTO> accountDTOS = accounts.getContent().stream()
                    .map(accountMapper::toDto)
                    .collect(Collectors.toList());

            return new ConfigResponse<>(accountDTOS, (int) accounts.getTotalElements());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public AccountDTO save(AccountDTO accountDTO, TokenDTO tokenDTO) {
        try {
            Account account = accountRepository.findByUsernameOrEmailAndIsDelete(
                    accountDTO.getUsername(),
                    accountDTO.getEmail(),
                    false
            );
            if (account != null) {
                throw new IllegalArgumentException("user name hoặc email đã tồn tại");
            }

            Account newAccount = accountMapper.toEntity(accountDTO);
            newAccount.setId(generateId.generateId(accountRepository, "EM"));
            newAccount.setPassword(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()));
            newAccount.setCreatedDate(LocalDateTime.now());
            newAccount.setLastModifiedDate(LocalDateTime.now());
            newAccount.setCreatedBy(tokenDTO.getFullname());
            newAccount.setLastModifiedBy(tokenDTO.getFullname());
            newAccount.setActive(true);
            newAccount.setIsDelete(false);

            return accountMapper.toDto(accountRepository.save(newAccount));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public AccountDTO update(AccountDTO accountDTO, TokenDTO tokenDTO) {
        try {
            Account account = accountRepository.findById(accountDTO.getId())
                    .orElseThrow(() -> new NoSuchElementException("Account not found by id: " + accountDTO.getId()));

            account.setAddress(accountDTO.getAddress());
            account.setEmail(accountDTO.getEmail());
            account.setBirthDay(accountDTO.getBirthDay());
            account.setFullname(accountDTO.getFullname());
            account.setPhone(accountDTO.getPhone());
            account.setRole(accountDTO.getRole());
            account.setActive(accountDTO.getActive());
            account.setLastModifiedDate(LocalDateTime.now());
            account.setLastModifiedBy(tokenDTO.getFullname());
            return accountMapper.toDto(accountRepository.save(account));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDTO checkLogin(Login login) {
        Account account = accountRepository.findByUsernameOrEmailAndIsDelete(
                login.getUserName(),
                login.getEmail(),
                false
        );
        if (account != null) {
            return AccountDTO.builder()
                    .id(account.getId())
                    .email(account.getEmail())
                    .phone(account.getPhone())
                    .password(account.getPassword())
                    .active(account.getActive())
                    .build();
        }
        return null;
    }

    @Override
    public AccountDTO getAccount(String id) {
        try {
            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Account not found by id: " + id));

            return accountMapper.toDto(account);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TokenDTO getAccountToken(String id) {
        try {
            return accountRepository.getAccountById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public AccountDTO delete(String id, TokenDTO tokenDTO) {
        try {
            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Account not found by id: " + id));

            account.setIsDelete(true);
            account.setLastModifiedDate(LocalDateTime.now());
            account.setLastModifiedBy(tokenDTO.getFullname());

            return accountMapper.toDto(accountRepository.save(account));
        }catch(NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
