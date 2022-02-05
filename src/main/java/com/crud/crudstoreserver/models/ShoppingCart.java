package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<PurchaseOrderItem> purchaseOrderItems;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Person person;

    private BigDecimal totalCost;

    private boolean isActive;

}
