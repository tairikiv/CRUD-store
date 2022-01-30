package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<OrderItem> orderItems;

    @OneToOne
    @NotNull
    private Users user;

    private BigDecimal totalCost;

    private boolean isActive;

}
