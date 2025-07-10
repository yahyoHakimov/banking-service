package com.example.bankingservice.Controller;

import com.example.bankingservice.Dto.TransferRequest;
import com.example.bankingservice.Entity.Account;
import com.example.bankingservice.Entity.Transaction;
import com.example.bankingservice.Repository.AccountRepository;
import com.example.bankingservice.Repository.TransactionRepository;
import com.example.bankingservice.Service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository repo;
    private final TransferService service;
    private final TransactionRepository txRepo;

    @PostMapping
    public Account create(@RequestBody Account account) {
        return repo.save(account); // Creates a new bank account
    }

    @GetMapping
    public List<Account> getAll() {
        return repo.findAll(); // Returns all bank accounts
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        service.transfer(request.getFromId(), request.getToId(), request.getAmount());
        return ResponseEntity.ok("Transfer successful");
    }


    @GetMapping("/transactions/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        return txRepo.findByFromAccountIdOrToAccountId(accountId, accountId);
    }
}
