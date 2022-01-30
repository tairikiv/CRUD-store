package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Users;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(Users user) {
        super("Cart not found for user: " + user.getFirstName());
    }
}
