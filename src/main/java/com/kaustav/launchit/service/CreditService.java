package com.kaustav.launchit.service;
import com.kaustav.launchit.db.Credit;
import com.kaustav.launchit.db.CreditRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service handling persistence for {@link Credit} entities.
 */
@Service
public class CreditService {
    private static final Logger log = LoggerFactory.getLogger(CreditService.class);

    private final CreditRepository repository;

    public CreditService(CreditRepository repository) {
        this.repository = repository;
    }

    /** Retrieve all credit records. */
    public List<Credit> findAll() {
        log.debug("Fetching all credits");
        return repository.findAll();
    }

    /** Find a credit by id. */
    public Credit find(int id) {
        log.debug("Fetching credit {}", id);
        return repository.findById(id).orElse(null);
    }

    /** Create a new credit entry. */
    public Credit create(Credit credit) {
        log.info("Creating credit for order {}", credit.getOrderId());
        return repository.save(credit);
    }

    /** Update an existing credit. */
    public Credit update(int id, Credit credit) {
        credit.setId(id);
        log.info("Updating credit {}", id);
        return repository.save(credit);
    }

    /** Delete a credit record. */
    public void delete(int id) {
        log.info("Deleting credit {}", id);
        repository.deleteById(id);
    }
}
