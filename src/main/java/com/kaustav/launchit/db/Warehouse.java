package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "warehouses")
/** Storage location for inventory. */
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
