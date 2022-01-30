package com.crud.crudstoreserver.exceptions;

public class PaymentNotFoundException extends Exception{
    public PaymentNotFoundException(Long id) {
        super("Payment (id:" + id + ") not found!");
    }
}
