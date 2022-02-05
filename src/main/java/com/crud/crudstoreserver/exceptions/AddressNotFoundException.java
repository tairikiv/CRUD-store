package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Person;

public class AddressNotFoundException extends Exception{
    public AddressNotFoundException(Long id) {
        super("Address (id:" + id + ") not found!");
    }

    public AddressNotFoundException(Person person) {
        super("Default address not found for person: " + person.getEmail());
    }
}
