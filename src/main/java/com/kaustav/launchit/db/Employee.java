package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
/** Entity representing a company employee. */
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    private String role;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "assigned_warehouse_id")
    private Integer assignedWarehouseId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
