package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.PaymentNotFoundException;
import com.crud.crudstoreserver.models.Payment;
import com.crud.crudstoreserver.models.PaymentType;

import java.util.List;

public interface PaymentService {

    List<Payment> findAllPayments();

    Payment findById(Long id) throws PaymentNotFoundException;

    List<Payment> findByPaymentType(PaymentType paymentType);

    Payment findByCardNumber(String cardNumber) throws PaymentNotFoundException;

    List<Payment> findByBank(String bank);

    void createPayment(Payment payment);

    void  updatePayment(Payment payment) throws PaymentNotFoundException;

    void deletePaymentById(Long id) throws PaymentNotFoundException;

    void restorePaymentById(Long id) throws PaymentNotFoundException;
}
