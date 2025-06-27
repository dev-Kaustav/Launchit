package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.Credit;
import com.kaustav.launchit.db.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller exposing credit-related endpoints.
 */
@RestController
@RequestMapping("/api/credits")
public class CreditController {
    private static final Logger log = LoggerFactory.getLogger(CreditController.class);

    private final CreditService service;

    public CreditController(CreditService service) {
        this.service = service;
    }

    /** List credits. */
    @GetMapping
    public List<Credit> all() {
        log.debug("GET /api/credits");
        return service.findAll();
    }

    /** Get credit by id. */
    @GetMapping("/{id}")
    public ResponseEntity<Credit> get(@PathVariable int id) {
        log.debug("GET /api/credits/{}", id);
        Credit credit = service.find(id);
        return credit != null ? ResponseEntity.ok(credit) : ResponseEntity.notFound().build();
    }

    /** Create a credit entry. */
    @PostMapping
    public Credit create(@RequestBody Credit credit) {
        log.info("POST /api/credits");
        return service.create(credit);
    }

    /** Update credit information. */
    @PutMapping("/{id}")
    public ResponseEntity<Credit> update(@PathVariable int id, @RequestBody Credit credit) {
        log.info("PUT /api/credits/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, credit));
    }

    /** Delete a credit. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/credits/{}", id);
        service.delete(id);
    }
}
