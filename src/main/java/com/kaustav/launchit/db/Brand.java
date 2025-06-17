package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "contact_email")
    private String contactEmail;

    private String industry;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
