package com.kaustav.launchit.db;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Data
@Embeddable
/** Composite primary key for {@link OrderItem}. */
public class OrderItemId implements Serializable {
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "sku_id")
    private int skuId;
}
