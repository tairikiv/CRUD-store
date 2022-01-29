package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.PaymentType;

public class PaymentNotFoundException extends Exception{
    public PaymentNotFoundException(Long id) {
        super("Payment (id: " + id + " not found!");
    }
    public PaymentNotFoundException(PaymentType paymentType) {
        super("Payment (paymentType: " + paymentType + " not found!");
    }

    public PaymentNotFoundException(String bank) {
        super("Payment (bank: " + bank + " not found!");
    }

    public PaymentNotFoundException(String cardNumber) {
        super("Payment (cardNumber: " + cardNumber + " not found!");
    }
}
