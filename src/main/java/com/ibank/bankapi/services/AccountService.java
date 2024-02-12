package com.ibank.bankapi.services;

import com.ibank.bankapi.dto.AccountDto;
import com.ibank.bankapi.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccount(Long id);

    AccountDto updateAccount(Long id, AccountDto accountDto);

    void deleteAccount(Long id);

    AccountDto deposit(Long id, Double amount);

    AccountDto withdraw(Long id, Double amount);

    AccountDto transfer(Long fromId, Long toId, Double amount);

    Double getAccountBalance(Long id);

    List<AccountDto> getAllAccounts();
    

}
