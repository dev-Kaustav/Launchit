package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Warehouse;
import com.kaustav.launchit.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller providing warehouse CRUD endpoints.
 */
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);

    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    /** List all warehouses. */
    @GetMapping
    public List<Warehouse> all() {
        log.debug("GET /api/warehouses");
        return service.findAll();
    }

    /** Retrieve a warehouse by id. */
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@PathVariable int id) {
        log.debug("GET /api/warehouses/{}", id);
        Warehouse wh = service.find(id);
        return wh != null ? ResponseEntity.ok(wh) : ResponseEntity.notFound().build();
    }

    /** Create a new warehouse. */
    @PostMapping
    public Warehouse create(@RequestBody Warehouse warehouse) {
        log.info("POST /api/warehouses");
        return service.create(warehouse);
    }

    /** Update an existing warehouse. */
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> update(@PathVariable int id, @RequestBody Warehouse warehouse) {
        log.info("PUT /api/warehouses/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, warehouse));
    }

    /** Delete a warehouse. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/warehouses/{}", id);
        service.delete(id);
    }
}
