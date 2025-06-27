package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Account;
import com.kaustav.launchit.db.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for CRUD operations on {@link Account} resources.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    /** List all accounts. */
    @GetMapping
    public List<Account> all() {
        log.debug("GET /api/accounts");
        return service.findAll();
    }

    /** Retrieve an account by id. */
    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable int id) {
        log.debug("GET /api/accounts/{}", id);
        Account account = service.find(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    /** Create a new account entry. */
    @PostMapping
    public Account create(@RequestBody Account account) {
        log.info("POST /api/accounts");
        return service.create(account);
    }

    /** Update an account. */
    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable int id, @RequestBody Account account) {
        log.info("PUT /api/accounts/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, account));
    }

    /** Delete an account by id. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/accounts/{}", id);
        service.delete(id);
    }
}
