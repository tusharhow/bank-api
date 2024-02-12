package com.ibank.bankapi.repositories;

import com.ibank.bankapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
