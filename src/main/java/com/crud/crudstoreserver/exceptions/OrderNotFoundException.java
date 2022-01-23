package com.crud.crudstoreserver.exceptions;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(Long id) {
        super("OrderItem (id:" + id + ") not found!");
    }
}
