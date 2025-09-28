package com.example.demo.service;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.Login;
import com.example.demo.dto.TokenDTO;
import com.example.demo.requests.filters.AccountFilters;
import com.example.demo.responses.ConfigResponse;

public interface AccountService {
    ConfigResponse<AccountDTO> getAccounts(Integer page, Integer size, AccountFilters filters);
    AccountDTO save(AccountDTO accountDTO, TokenDTO tokenDTO);
    AccountDTO update(AccountDTO accountDTO, TokenDTO tokenDTO);
    AccountDTO checkLogin(Login login);
    AccountDTO getAccount(String id);
    TokenDTO getAccountToken(String id);
    AccountDTO delete(String id, TokenDTO tokenDTO);

}
