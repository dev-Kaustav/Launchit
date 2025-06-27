package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "credits")
/** Entity representing credit extended to retailers or customers. */
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "credited_amount")
    private Double creditedAmount;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private Boolean settled;

    @Column(name = "party_type")
    private String partyType;
}
