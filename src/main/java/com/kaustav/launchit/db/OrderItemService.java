package com.kaustav.launchit.db;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service for handling operations on {@link OrderItem} entities.
 */
@Service
public class OrderItemService {
    private static final Logger log = LoggerFactory.getLogger(OrderItemService.class);

    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    /** Retrieve all order items. */
    public List<OrderItem> findAll() {
        log.debug("Fetching all order items");
        return repository.findAll();
    }

    /** Find an order item by composite id. */
    public OrderItem find(OrderItemId id) {
        log.debug("Fetching order item {}", id);
        return repository.findById(id).orElse(null);
    }

    /** Create a new order item. */
    public OrderItem create(OrderItem item) {
        log.info("Creating order item for order {} and SKU {}", item.getId().getOrderId(), item.getId().getSkuId());
        return repository.save(item);
    }

    /** Update an order item. */
    public OrderItem update(OrderItemId id, OrderItem item) {
        item.setId(id);
        log.info("Updating order item {}", id);
        return repository.save(item);
    }

    /** Delete an order item. */
    public void delete(OrderItemId id) {
        log.info("Deleting order item {}", id);
        repository.deleteById(id);
    }
}
