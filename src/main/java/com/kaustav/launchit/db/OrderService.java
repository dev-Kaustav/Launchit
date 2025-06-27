package com.kaustav.launchit.db;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service for persistence operations on {@link OrderEntity}.
 */
@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    /** Retrieve all orders. */
    public List<OrderEntity> findAll() {
        log.debug("Fetching all orders");
        return repository.findAll();
    }

    /** Find an order by id. */
    public OrderEntity find(int id) {
        log.debug("Fetching order {}", id);
        return repository.findById(id).orElse(null);
    }

    /** Create a new order. */
    public OrderEntity create(OrderEntity order) {
        log.info("Creating order of type {}", order.getType());
        return repository.save(order);
    }

    /** Update an existing order. */
    public OrderEntity update(int id, OrderEntity order) {
        order.setId(id);
        log.info("Updating order {}", id);
        return repository.save(order);
    }

    /** Delete an order by id. */
    public void delete(int id) {
        log.info("Deleting order {}", id);
        repository.deleteById(id);
    }
}
