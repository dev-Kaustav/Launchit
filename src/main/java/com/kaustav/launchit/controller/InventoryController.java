package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Inventory;
import com.kaustav.launchit.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for inventory operations.
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    /** List all inventory entries. */
    @GetMapping
    public List<Inventory> all() {
        log.debug("GET /api/inventory");
        return service.findAll();
    }

    /** Get inventory by id. */
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> get(@PathVariable int id) {
        log.debug("GET /api/inventory/{}", id);
        Inventory inv = service.find(id);
        return inv != null ? ResponseEntity.ok(inv) : ResponseEntity.notFound().build();
    }

    /** Create inventory entry. */
    @PostMapping
    public Inventory create(@RequestBody Inventory inventory) {
        log.info("POST /api/inventory");
        return service.create(inventory);
    }

    /** Update inventory entry. */
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> update(@PathVariable int id, @RequestBody Inventory inventory) {
        log.info("PUT /api/inventory/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, inventory));
    }

    /** Delete inventory entry. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/inventory/{}", id);
        service.delete(id);
    }
}
