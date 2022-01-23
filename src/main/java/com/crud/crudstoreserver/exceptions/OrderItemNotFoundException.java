package com.crud.crudstoreserver.exceptions;

public class OrderItemNotFoundException extends Exception{
    public OrderItemNotFoundException(Long id) {
        super("OrderItem (id:" + id + ") not found!");
    }


}
