package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Retailer;
import com.kaustav.launchit.db.RetailerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller exposing retailer CRUD endpoints.
 */
@RestController
@RequestMapping("/api/retailers")
public class RetailerController {
    private static final Logger log = LoggerFactory.getLogger(RetailerController.class);

    private final RetailerService service;

    public RetailerController(RetailerService service) {
        this.service = service;
    }

    /** List all retailers. */
    @GetMapping
    public List<Retailer> all() {
        log.debug("GET /api/retailers");
        return service.findAll();
    }

    /** Retrieve a retailer by id. */
    @GetMapping("/{id}")
    public ResponseEntity<Retailer> get(@PathVariable int id) {
        log.debug("GET /api/retailers/{}", id);
        Retailer r = service.find(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    /** Create a new retailer. */
    @PostMapping
    public Retailer create(@RequestBody Retailer retailer) {
        log.info("POST /api/retailers");
        return service.create(retailer);
    }

    /** Update retailer details. */
    @PutMapping("/{id}")
    public ResponseEntity<Retailer> update(@PathVariable int id, @RequestBody Retailer retailer) {
        log.info("PUT /api/retailers/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, retailer));
    }

    /** Delete a retailer by id. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/retailers/{}", id);
        service.delete(id);
    }
}
