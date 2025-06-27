package com.kaustav.launchit.service;
import com.kaustav.launchit.db.Inventory;
import com.kaustav.launchit.db.InventoryRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service providing CRUD operations for {@link Inventory} entities.
 */
@Service
public class InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    /** Return all inventory entries. */
    public List<Inventory> findAll() {
        log.debug("Fetching all inventory records");
        return repository.findAll();
    }

    /** Find inventory by id. */
    public Inventory find(int id) {
        log.debug("Fetching inventory {}", id);
        return repository.findById(id).orElse(null);
    }

    /** Create an inventory record. */
    public Inventory create(Inventory inventory) {
        log.info("Creating inventory entry for SKU {}", inventory.getSkuId());
        return repository.save(inventory);
    }

    /** Update inventory. */
    public Inventory update(int id, Inventory inventory) {
        inventory.setId(id);
        log.info("Updating inventory {}", id);
        return repository.save(inventory);
    }

    /** Delete inventory record. */
    public void delete(int id) {
        log.info("Deleting inventory {}", id);
        repository.deleteById(id);
    }
}
