package com.ibank.bankapi.controllers;

import com.ibank.bankapi.dto.AccountDto;
import com.ibank.bankapi.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        AccountDto account = accountService.getAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        AccountDto updatedAccount = accountService.updateAccount(id, accountDto);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account with id " + id + " has been deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestParam Double amount) {

        AccountDto account = accountService.deposit(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);


    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestParam Double amount) {
        AccountDto account = accountService.withdraw(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    @PutMapping("/{fromId}/transfer/{toId}")
    public ResponseEntity<AccountDto> transfer(@PathVariable Long fromId, @PathVariable Long toId, @RequestParam Double amount) {
        AccountDto account = accountService.transfer(fromId, toId, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable Long id) {
        Double balance = accountService.getAccountBalance(id);
        return new ResponseEntity<>(balance, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

}
