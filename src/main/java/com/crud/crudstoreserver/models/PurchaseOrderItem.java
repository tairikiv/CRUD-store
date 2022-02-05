package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Product product;

    @Min(1)
    private int quantity;

    @Min(1)
    private BigDecimal totalPrice;

    private boolean isActive;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Person person;
}
