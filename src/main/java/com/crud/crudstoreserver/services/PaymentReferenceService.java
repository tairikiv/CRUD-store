package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.PaymentReferenceNotFoundException;
import com.crud.crudstoreserver.models.PaymentReference;

import java.util.List;

public interface PaymentReferenceService {

    List<PaymentReference> findAllPaymentReferences();

    PaymentReference findById(Long id) throws PaymentReferenceNotFoundException;

    void createPaymentReference(PaymentReference paymentReference);

    void updatePaymentReference(PaymentReference paymentReference) throws PaymentReferenceNotFoundException;

    void deletePaymentReferenceById(Long id) throws PaymentReferenceNotFoundException;

    void restorePaymentReferenceById(Long id) throws PaymentReferenceNotFoundException;
}
