package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Sku;
import com.kaustav.launchit.db.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST endpoints for SKU management.
 */
@RestController
@RequestMapping("/api/skus")
public class SkuController {
    private static final Logger log = LoggerFactory.getLogger(SkuController.class);

    private final SkuService service;

    public SkuController(SkuService service) {
        this.service = service;
    }

    /** List all SKUs. */
    @GetMapping
    public List<Sku> all() {
        log.debug("GET /api/skus");
        return service.findAll();
    }

    /** Retrieve a SKU by id. */
    @GetMapping("/{id}")
    public ResponseEntity<Sku> get(@PathVariable int id) {
        log.debug("GET /api/skus/{}", id);
        Sku sku = service.find(id);
        return sku != null ? ResponseEntity.ok(sku) : ResponseEntity.notFound().build();
    }

    /** Create a new SKU. */
    @PostMapping
    public Sku create(@RequestBody Sku sku) {
        log.info("POST /api/skus");
        return service.create(sku);
    }

    /** Update a SKU. */
    @PutMapping("/{id}")
    public ResponseEntity<Sku> update(@PathVariable int id, @RequestBody Sku sku) {
        log.info("PUT /api/skus/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, sku));
    }

    /** Delete a SKU by id. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/skus/{}", id);
        service.delete(id);
    }
}
