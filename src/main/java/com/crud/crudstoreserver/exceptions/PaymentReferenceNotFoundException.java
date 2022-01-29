package com.crud.crudstoreserver.exceptions;

public class PaymentReferenceNotFoundException extends Exception{
    public PaymentReferenceNotFoundException(Long id) {
        super("PaymentReference (id:" + id + ") not found!");
    }
}
