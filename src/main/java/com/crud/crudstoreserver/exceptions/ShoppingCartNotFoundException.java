package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Person;

public class ShoppingCartNotFoundException extends Exception {
    public ShoppingCartNotFoundException(Person person) {
        super("Shopping Cart not found for person: " + person.getFirstName());
    }
}
