package com.ibank.bankapi.services.impl;

import com.ibank.bankapi.dto.AccountDto;
import com.ibank.bankapi.entity.Account;
import com.ibank.bankapi.mapper.AccountMapper;
import com.ibank.bankapi.repositories.AccountRepository;
import com.ibank.bankapi.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccount(Long id) {
        AccountDto accountDto = accountRepository.findById(id).map(AccountMapper::toAccountDto).orElse(null);
        return accountDto;
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        AccountDto account = accountRepository.findById(id).map(AccountMapper::toAccountDto).orElse(null);

        if (account != null) {
            account.setId(id);
            Account updatedAccount = accountRepository.save(AccountMapper.toAccount(account));
            return AccountMapper.toAccountDto(updatedAccount);
        }
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        AccountDto account = accountRepository.findById(id).map(AccountMapper::toAccountDto).orElse(null);
        if (account != null) {
            accountRepository.deleteById(id);
        }

    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        AccountDto account = accountRepository.findById(id).map(AccountMapper::toAccountDto).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            Account updatedAccount = accountRepository.save(AccountMapper.toAccount(account));
            return AccountMapper.toAccountDto(updatedAccount);
        }
        return null;
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {

        AccountDto account = accountRepository.findById(id).map(AccountMapper::toAccountDto).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance() - amount);
            Account updatedAccount = accountRepository.save(AccountMapper.toAccount(account));
            return AccountMapper.toAccountDto(updatedAccount);
        }
        return null;
    }

    @Override
    public AccountDto transfer(Long fromId, Long toId, Double amount) {
        AccountDto fromAccount = accountRepository.findById(fromId).map(AccountMapper::toAccountDto).orElse(null);
        AccountDto toAccount = accountRepository.findById(toId).map(AccountMapper::toAccountDto).orElse(null);
        if (fromAccount != null && toAccount != null) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            Account updatedFromAccount = accountRepository.save(AccountMapper.toAccount(fromAccount));
            return AccountMapper.toAccountDto(updatedFromAccount);
        }
        return null;
    }

    @Override
    public Double getAccountBalance(Long id) {
        AccountDto accountBalance = accountRepository.findById(id).map(AccountMapper::toAccountDto).orElse(null);
        if (accountBalance != null) {
            return accountBalance.getBalance();
        }
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountRepository.findAll().stream().map(AccountMapper::toAccountDto).toList();
    }
}
