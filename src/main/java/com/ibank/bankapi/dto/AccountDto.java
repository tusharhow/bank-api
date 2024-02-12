package com.ibank.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String accountName;
    private String accountNumber;
    private Double balance;
}
