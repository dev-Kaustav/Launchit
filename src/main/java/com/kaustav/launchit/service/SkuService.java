package com.kaustav.launchit.service;
import com.kaustav.launchit.db.Sku;
import com.kaustav.launchit.db.SkuRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service for CRUD operations on SKU entities.
 */
@Service
public class SkuService {
    private static final Logger log = LoggerFactory.getLogger(SkuService.class);

    private final SkuRepository repository;

    public SkuService(SkuRepository repository) {
        this.repository = repository;
    }

    /** Retrieve all SKUs. */
    public List<Sku> findAll() {
        log.debug("Fetching all SKUs");
        return repository.findAll();
    }

    /** Find a SKU by id. */
    public Sku find(int id) {
        log.debug("Fetching SKU {}", id);
        return repository.findById(id).orElse(null);
    }

    /** Create a new SKU. */
    public Sku create(Sku sku) {
        log.info("Creating SKU {}", sku.getName());
        return repository.save(sku);
    }

    /** Update a SKU. */
    public Sku update(int id, Sku sku) {
        sku.setId(id);
        log.info("Updating SKU {}", id);
        return repository.save(sku);
    }

    /** Delete a SKU. */
    public void delete(int id) {
        log.info("Deleting SKU {}", id);
        repository.deleteById(id);
    }
}
