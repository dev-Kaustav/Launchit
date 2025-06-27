package com.kaustav.launchit.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
/** Entity representing a purchase or sales order. */
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(name = "retailer_id")
    private Integer retailerId;

    @Column(name = "salesman_id")
    private Integer salesmanId;

    @Column(name = "accountant_id")
    private Integer accountantId;

    private String status;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;
}
