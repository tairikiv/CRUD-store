package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Users;

public class AddressNotFoundException extends Exception{
    public AddressNotFoundException(Long id) {
        super("Address (id:" + id + ") not found!");
    }

    public AddressNotFoundException(Users user) {
        super("Default address not found for user: " + user.getEmail());
    }
}
