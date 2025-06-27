package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.OrderEntity;
import com.kaustav.launchit.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller providing endpoints to manage orders.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    /** List all orders. */
    @GetMapping
    public List<OrderEntity> all() {
        log.debug("GET /api/orders");
        return service.findAll();
    }

    /** Retrieve an order by id. */
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> get(@PathVariable int id) {
        log.debug("GET /api/orders/{}", id);
        OrderEntity order = service.find(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    /** Create a new order. */
    @PostMapping
    public OrderEntity create(@RequestBody OrderEntity order) {
        log.info("POST /api/orders");
        return service.create(order);
    }

    /** Update an existing order. */
    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable int id, @RequestBody OrderEntity order) {
        log.info("PUT /api/orders/{}", id);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, order));
    }

    /** Delete an order. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("DELETE /api/orders/{}", id);
        service.delete(id);
    }
}
