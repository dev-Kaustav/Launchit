package com.kaustav.launchit.db;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
/** Line item belonging to an order. */
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    private int quantity;

    @Column(name = "price_per_unit")
    private double pricePerUnit;

    @Column(name = "total_price")
    private double totalPrice;
}
