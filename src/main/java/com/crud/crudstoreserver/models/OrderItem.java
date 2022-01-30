package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @NotNull
    private Product product;

    @Min(1)
    private int quantity;

    @Min(1)
    private BigDecimal totalPrice;

    private boolean isActive;

    @OneToOne
    @NotNull
    private Users user;
}
