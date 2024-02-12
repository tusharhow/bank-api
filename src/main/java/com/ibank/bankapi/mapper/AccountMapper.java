package com.ibank.bankapi.mapper;


import com.ibank.bankapi.dto.AccountDto;
import com.ibank.bankapi.entity.Account;

public class AccountMapper {
    public static AccountDto toAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountName(),
                account.getAccountNumber(),
                account.getBalance().doubleValue()
        );
    }

    public static Account toAccount(AccountDto accountDto) {
        return new Account(
                accountDto.getId(),
                accountDto.getAccountName(),
                accountDto.getAccountNumber(),
                accountDto.getBalance().longValue()
        );

    }
}
