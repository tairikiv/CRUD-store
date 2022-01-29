package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.PaymentType;

public class PaymentNotFoundException extends Exception {
    public PaymentNotFoundException(Long id) {
        super("Payment (id: " + id + " not found!");
    }


    public PaymentNotFoundException(String cardNumber) {
        super("Payment (card number: " + cardNumber+ ") not found!");
    }
}
