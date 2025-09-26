package com.example.demo.service.impl;

import com.example.demo.domain.Account;
import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.Login;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Account> account = null;
        if (accountDTO.getId() != null && !accountDTO.getId().isEmpty()) {
            account = accountRepository.findById(accountDTO.getId());
            if (!account.isPresent()) {
                throw new RuntimeException("id khoong tồn tài trong hệ thống");
            }
            String encodedPassword = Base64.getEncoder().encodeToString(accountDTO.getPassword().getBytes());
            if (!encodedPassword.equals(account.get().getPassword())) {
                accountDTO.setPassword(encodedPassword);
            }else {
                accountDTO.setPassword(Base64.getEncoder().encodeToString(accountDTO.getPassword().getBytes()));
            }
            accountDTO.setLastModifiedBy(accountDTO.getEmail());
            accountDTO.setLastModifiedDate(now);
        } else {

            try {
                account = accountRepository.findByUsername(accountDTO.getUsername());
            } catch (Exception e) {
                throw new RuntimeException("Không lấy được thông tin account theo useName");
            }

            try {
                account = accountRepository.findByEmail(accountDTO.getEmail());
            } catch (Exception e) {
                throw new RuntimeException("không lấy đưọc thông tin account theo  email");
            }

            if (account.isPresent()) {
                throw new RuntimeException("userName và email đã tồn tài trong hệ thống");
            }
            String newId = generateNewId();
            accountDTO.setId(newId);
            accountDTO.setPassword(Base64.getEncoder().encodeToString(accountDTO.getPassword().getBytes()));
            accountDTO.setCreatedBy(accountDTO.getEmail());
            accountDTO.setCreatedDate(now);
        }
        Account account1 = accountMapper.toEntity(accountDTO);
        account1 = accountRepository.save(account1);
        return accountMapper.toDto(account1);
    }

    @Override
    public String checkLogin(Login login) {
        Optional<Account> account = Optional.empty();

        try {
            if (login.getUserName() != null) {
                account = accountRepository.findByUsername(login.getUserName());
            }
            if (!account.isPresent() && login.getEmail() != null) {
                account = accountRepository.findByEmail(login.getEmail());
            }
        } catch (Exception e) {
            throw new RuntimeException("Không lấy được thông tin account", e);
        }

        if (!account.isPresent()) {
            return "";
        }

        Account account1 = account.get();

        if (!account1.getActive()) {
            return "";
        }
        String encodedPassword = Base64.getEncoder().encodeToString(login.getPassword().getBytes());
        if (!encodedPassword.equals(account1.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }
        return account.get().getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountDTO> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(accountMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountDTO> findOne(String id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto);
    }

    @Override
    public void delete(String id) {
        accountRepository.deleteById(id);
    }

    private String generateNewId() {
        List<Account> list = accountRepository.findTopByIdOrderByIdDesc();
        if (list.isEmpty()) {
            return "AC001";
        } else {
            String lastId = list.get(0).getId();
            int num = Integer.parseInt(lastId.substring(2));
            num++;
            return String.format("AC%03d", num); // AC008
        }
    }
}
