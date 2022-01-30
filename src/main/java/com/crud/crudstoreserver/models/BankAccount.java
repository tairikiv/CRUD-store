package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardType paymentType;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String bank;

    private LocalDate cardExpiry;
    private boolean isDefault;
    private boolean isActive;
}
