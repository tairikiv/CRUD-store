package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PaymentReference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    private PaymentType paymentType;

    private double totalCost;

    @Enumerated
    private PaymentReferenceStatus paymentReferenceStatus;
}
