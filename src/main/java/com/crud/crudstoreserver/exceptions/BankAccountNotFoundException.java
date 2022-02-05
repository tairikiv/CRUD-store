package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Person;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(Long id) {
        super("Payment (id: " + id + " not found!");
    }


    public BankAccountNotFoundException(String cardNumber) {
        super("Bank account not found for person with card number: " + cardNumber );
    }

    public BankAccountNotFoundException(Person person) {
        super("Default bank account not found for person: " + person.getEmail());
    }
}
