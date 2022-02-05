package com.crud.crudstoreserver.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Person person;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @ManyToMany
    private List<PurchaseOrderItem> purchaseOrderItems;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Payment payment;

    private boolean isActive;
}
