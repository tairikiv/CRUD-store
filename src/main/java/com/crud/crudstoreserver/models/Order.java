package com.crud.crudstoreserver.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int userAccount;

    private BigDecimal totalCost;

    @OneToOne
    private Address address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @OneToOne
    private OrderItem orderItem;

    @Enumerated
    private OrderStatus orderStatus;

    @OneToOne
    private PaymentReference paymentReference;
}
