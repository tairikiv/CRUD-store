package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.BankAccountNotFoundException;
import com.crud.crudstoreserver.exceptions.PaymentFailedException;
import com.crud.crudstoreserver.exceptions.PaymentNotFoundException;
import com.crud.crudstoreserver.models.Payment;
import com.crud.crudstoreserver.models.Person;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    List<Payment> findAllPayments();

    Payment findById(Long id) throws PaymentNotFoundException;

    Payment initiatePayment(Person person, BigDecimal totalSum) throws BankAccountNotFoundException, PaymentFailedException;

    void debitPayment(Payment payment);
    Payment createPayment(Payment payment);

    void updatePayment(Payment payment) throws PaymentNotFoundException;

    void deletePaymentById(Long id) throws PaymentNotFoundException;

    void restorePaymentById(Long id) throws PaymentNotFoundException;
}
