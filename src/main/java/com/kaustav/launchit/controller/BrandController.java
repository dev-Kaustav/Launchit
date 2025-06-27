package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Brand;
import com.kaustav.launchit.db.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller exposing endpoints for {@link Brand} CRUD operations.
 */
@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    /**
     * Retrieve all brands.
     */
    @GetMapping
    public List<Brand> all() {
        log.debug("GET /api/brands");
        return service.findAll();
    }

    /**
     * Get a single brand by id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Brand> get(@PathVariable int id) {
        log.debug("GET /api/brands/{}", id);
        Brand brand = service.find(id);
        return brand != null ? ResponseEntity.ok(brand) : ResponseEntity.notFound().build();
    }

    /**
     * Create a new brand.
     */
    @PostMapping
    public Brand create(@RequestBody Brand brand) {
        log.info("POST /api/brands - creating {}", brand.getName());
        return service.create(brand);
    }

    /**
     * Update an existing brand.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Brand> update(@PathVariable int id, @RequestBody Brand brand) {
        log.info("PUT /api/brands/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, brand));
    }

    /**
     * Delete a brand by id.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/brands/{}", id);
        service.delete(id);
    }
}
