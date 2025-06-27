package com.kaustav.launchit.service;
import com.kaustav.launchit.db.Account;
import com.kaustav.launchit.db.AccountRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service for CRUD operations on {@link Account} entities.
 */
@Service
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all accounts.
     */
    public List<Account> findAll() {
        log.debug("Fetching all accounts");
        return repository.findAll();
    }

    /**
     * Find an account by id.
     */
    public Account find(int id) {
        log.debug("Fetching account {}", id);
        return repository.findById(id).orElse(null);
    }

    /**
     * Create a new account record.
     */
    public Account create(Account account) {
        log.info("Creating account for order {}", account.getOrderId());
        return repository.save(account);
    }

    /**
     * Update the account with the given id.
     */
    public Account update(int id, Account account) {
        account.setId(id);
        log.info("Updating account {}", id);
        return repository.save(account);
    }

    /**
     * Delete an account.
     */
    public void delete(int id) {
        log.info("Deleting account {}", id);
        repository.deleteById(id);
    }
}
