package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class PaymentReference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    private PaymentReferenceStatus paymentReferenceStatus;
}
