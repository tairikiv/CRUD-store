package com.crud.crudstoreserver.exceptions;

public class UsersNotFoundException extends Exception {
    public UsersNotFoundException(Long id) {
        super("User (id: " + id + " not found!");
    }

}
