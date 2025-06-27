package com.kaustav.launchit.controller;

import com.kaustav.launchit.db.OrderItem;
import com.kaustav.launchit.db.OrderItemId;
import com.kaustav.launchit.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for endpoints dealing with order items.
 */
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private static final Logger log = LoggerFactory.getLogger(OrderItemController.class);

    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    /** List all order items. */
    @GetMapping
    public List<OrderItem> all() {
        log.debug("GET /api/order-items");
        return service.findAll();
    }

    /**
     * Get an order item by order and SKU identifiers.
     */
    @GetMapping("/{orderId}/{skuId}")
    public ResponseEntity<OrderItem> get(@PathVariable int orderId, @PathVariable int skuId) {
        log.debug("GET /api/order-items/{}/{}", orderId, skuId);
        OrderItemId id = new OrderItemId();
        id.setOrderId(orderId);
        id.setSkuId(skuId);
        OrderItem item = service.find(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    /** Create a new order item. */
    @PostMapping
    public OrderItem create(@RequestBody OrderItem item) {
        log.info("POST /api/order-items");
        return service.create(item);
    }

    /** Update an order item. */
    @PutMapping("/{orderId}/{skuId}")
    public ResponseEntity<OrderItem> update(@PathVariable int orderId, @PathVariable int skuId, @RequestBody OrderItem item) {
        log.info("PUT /api/order-items/{}/{}", orderId, skuId);
        OrderItemId id = new OrderItemId();
        id.setOrderId(orderId);
        id.setSkuId(skuId);
        if (service.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        return ResponseEntity.ok(service.update(id, item));
    }

    /** Delete an order item. */
    @DeleteMapping("/{orderId}/{skuId}")
    public void delete(@PathVariable int orderId, @PathVariable int skuId) {
        log.info("DELETE /api/order-items/{}/{}", orderId, skuId);
        OrderItemId id = new OrderItemId();
        id.setOrderId(orderId);
        id.setSkuId(skuId);
        service.delete(id);
    }
}
