package com.example.bankingservice.Repository;

import com.example.bankingservice.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

