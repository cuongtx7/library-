package com.example.demo.mapper;

import com.example.demo.domain.Account;
import com.example.demo.dto.AccountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {
}
