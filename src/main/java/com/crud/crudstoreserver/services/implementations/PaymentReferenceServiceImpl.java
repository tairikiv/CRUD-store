package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.PaymentReferenceNotFoundException;
import com.crud.crudstoreserver.models.PaymentReference;
import com.crud.crudstoreserver.repositories.PaymentReferenceRepository;
import com.crud.crudstoreserver.services.PaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentReferenceServiceImpl implements PaymentReferenceService {
    @Autowired
    private PaymentReferenceRepository paymentReferenceRepository;

    @Override
    public List<PaymentReference> findAllPaymentReferences() {
        return paymentReferenceRepository.findAll();
    }

    @Override
    public PaymentReference findById(Long id) throws PaymentReferenceNotFoundException {
        Optional<PaymentReference> paymentReferenceOptional = paymentReferenceRepository.findById(id);

        if (paymentReferenceOptional.isEmpty()) {
            throw new PaymentReferenceNotFoundException(id);
        }
        return paymentReferenceOptional.get();
    }

    @Override
    public void createPaymentReference(PaymentReference paymentReference) {
        paymentReferenceRepository.save(paymentReference);
    }

    @Override
    public void updatePaymentReference(PaymentReference paymentReference) throws PaymentReferenceNotFoundException {
        if (!paymentReferenceRepository.existsById(paymentReference.getId())) {
            throw new PaymentReferenceNotFoundException(paymentReference.getId());
        }
        paymentReferenceRepository.saveAndFlush(paymentReference);

    }

    @Override
    public void deletePaymentReferenceById(Long id) throws PaymentReferenceNotFoundException {
        Optional<PaymentReference> paymentReferenceOptional = Optional.ofNullable(findById(id));

        if (paymentReferenceOptional.isEmpty()) {
            throw new PaymentReferenceNotFoundException(id);
        } else {
            PaymentReference paymentReference = paymentReferenceOptional.get();
            paymentReference.setActive(false);
            paymentReferenceRepository.saveAndFlush(paymentReference);
        }
    }

    @Override
    public void restorePaymentReferenceById(Long id) throws PaymentReferenceNotFoundException {
        Optional<PaymentReference> paymentReferenceOptional = Optional.ofNullable(findById(id));

        if (paymentReferenceOptional.isEmpty()) {
            throw new PaymentReferenceNotFoundException(id);
        } else {
            PaymentReference paymentReference = paymentReferenceOptional.get();
            paymentReference.setActive(true);
            paymentReferenceRepository.saveAndFlush(paymentReference);
        }
    }
}
