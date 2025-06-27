package com.kaustav.launchit.service;
import com.kaustav.launchit.db.Warehouse;
import com.kaustav.launchit.db.WarehouseRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service for operations on {@link Warehouse} entities.
 */
@Service
public class WarehouseService {
    private static final Logger log = LoggerFactory.getLogger(WarehouseService.class);

    private final WarehouseRepository repository;

    public WarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }

    /** Retrieve all warehouses. */
    public List<Warehouse> findAll() {
        log.debug("Fetching all warehouses");
        return repository.findAll();
    }

    /** Find a warehouse by id. */
    public Warehouse find(int id) {
        log.debug("Fetching warehouse {}", id);
        return repository.findById(id).orElse(null);
    }

    /** Create a new warehouse. */
    public Warehouse create(Warehouse warehouse) {
        log.info("Creating warehouse at {}", warehouse.getLocation());
        return repository.save(warehouse);
    }

    /** Update warehouse data. */
    public Warehouse update(int id, Warehouse warehouse) {
        warehouse.setId(id);
        log.info("Updating warehouse {}", id);
        return repository.save(warehouse);
    }

    /** Delete a warehouse. */
    public void delete(int id) {
        log.info("Deleting warehouse {}", id);
        repository.deleteById(id);
    }
}
