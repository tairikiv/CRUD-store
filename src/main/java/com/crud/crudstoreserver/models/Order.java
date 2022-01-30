package com.crud.crudstoreserver.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @NotNull
    private Users user;

    @OneToOne
    private Address address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @ManyToMany
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    @NotNull
    private Payment payment;

    private boolean isActive;
}
