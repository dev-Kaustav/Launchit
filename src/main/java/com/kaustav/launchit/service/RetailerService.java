package com.kaustav.launchit.service;
import com.kaustav.launchit.db.Retailer;
import com.kaustav.launchit.db.RetailerRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service for {@link Retailer} entity CRUD operations.
 */
@Service
public class RetailerService {
    private static final Logger log = LoggerFactory.getLogger(RetailerService.class);

    private final RetailerRepository repository;

    public RetailerService(RetailerRepository repository) {
        this.repository = repository;
    }

    /** Retrieve all retailers. */
    public List<Retailer> findAll() {
        log.debug("Fetching all retailers");
        return repository.findAll();
    }

    /** Find a retailer by id. */
    public Retailer find(int id) {
        log.debug("Fetching retailer {}", id);
        return repository.findById(id).orElse(null);
    }

    /** Create a new retailer. */
    public Retailer create(Retailer retailer) {
        log.info("Creating retailer {}", retailer.getName());
        return repository.save(retailer);
    }

    /** Update retailer information. */
    public Retailer update(int id, Retailer retailer) {
        retailer.setId(id);
        log.info("Updating retailer {}", id);
        return repository.save(retailer);
    }

    /** Delete a retailer by id. */
    public void delete(int id) {
        log.info("Deleting retailer {}", id);
        repository.deleteById(id);
    }
}
