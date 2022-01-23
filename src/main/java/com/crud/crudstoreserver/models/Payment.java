package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private String cardNumber;
    private String bank;

    private boolean isDefault;

}
