package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Users;

import java.util.function.Supplier;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(Long id) {
        super("Payment (id: " + id + " not found!");
    }


    public BankAccountNotFoundException(String cardNumber) {
        super("Bank account not found for user with card number: " + cardNumber );
    }

    public BankAccountNotFoundException(Users user) {
        super("Default bank account not found for user: " + user.getEmail());
    }
}
