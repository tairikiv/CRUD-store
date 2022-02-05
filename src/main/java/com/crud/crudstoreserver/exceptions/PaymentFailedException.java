package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Payment;

public class PaymentFailedException extends Exception {
    public PaymentFailedException(Payment payment) {
        super("Payment failed! " + payment.getPaymentStatus());
    }
}
