package com.crud.crudstoreserver.exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(Long id) {
        super("Person (id: " + id + " not found!");
    }

}
