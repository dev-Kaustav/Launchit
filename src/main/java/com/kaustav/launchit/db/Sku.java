package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "skus")
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "sku_code", unique = true)
    private String skuCode;

    @Column(name = "brand_id")
    private int brandId;

    private String type;

    @Column(name = "mfg_date")
    private LocalDate mfgDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    private String status;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
