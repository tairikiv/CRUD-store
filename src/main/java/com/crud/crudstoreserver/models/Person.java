package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    private boolean isAdmin;

    @ManyToMany
    @NotBlank
    private List<Address> addresses;

    @ManyToMany
    @NotNull
    private List<BankAccount> bankAccounts;

    private String phoneNumber;

    private boolean isActive;
}
