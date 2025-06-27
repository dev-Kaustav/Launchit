package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "retailers")
/** Retail outlet that places orders. */
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "assigned_salesman_id")
    private Integer assignedSalesmanId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
