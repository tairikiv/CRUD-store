package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Product;

public class OrderItemNotFoundException extends Exception{
    public OrderItemNotFoundException(Long id) {
        super("OrderItem (id:" + id + ") not found!");
    }

    public OrderItemNotFoundException(Product product) {
        super("OrderItem (id: " + product + ") not found!" );

    }
}
