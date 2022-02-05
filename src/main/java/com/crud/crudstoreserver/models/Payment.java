package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private BankAccount bankAccount;

    @Min(1)
    private BigDecimal totalSum;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private boolean isActive;
}
