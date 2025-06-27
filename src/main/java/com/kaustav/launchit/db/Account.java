package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
/** Entity capturing payments for orders. */
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "transaction_reference")
    private String transactionReference;

    private String status;
}
