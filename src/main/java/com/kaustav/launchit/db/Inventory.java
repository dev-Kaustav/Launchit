package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory")
/** Entity representing stock information for a SKU at a warehouse. */
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;

    @Column(name = "quantity_reserved")
    private Integer quantityReserved;

    @Column(name = "quantity_damaged")
    private Integer quantityDamaged;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
