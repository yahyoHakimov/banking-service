package com.example.bankingservice.Service;

import com.example.bankingservice.Entity.Account;
import com.example.bankingservice.Entity.Transaction;
import com.example.bankingservice.Repository.AccountRepository;
import com.example.bankingservice.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final AccountRepository accountRepo;
    private final TransactionRepository txRepo;

    @Transactional // Ensures DB changes succeed or rollback together
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        // Load both accounts
        Account from = accountRepo.findById(fromId).orElseThrow();
        Account to = accountRepo.findById(toId).orElseThrow();

        // Check if sender has enough money
        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Update balances
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        // Save updated accounts
        accountRepo.save(from);
        accountRepo.save(to);

        // Log the transaction
        Transaction tx = new Transaction();
        tx.setFromAccountId(fromId);
        tx.setToAccountId(toId);
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());

        txRepo.save(tx);
    }
}
