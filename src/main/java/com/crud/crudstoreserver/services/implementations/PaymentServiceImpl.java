package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.PaymentNotFoundException;
import com.crud.crudstoreserver.models.Payment;
import com.crud.crudstoreserver.models.PaymentType;
import com.crud.crudstoreserver.repositories.PaymentRepository;
import com.crud.crudstoreserver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(Long id) throws PaymentNotFoundException {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException(id);
        }
        return paymentOptional.get();
    }

    @Override
    public Payment findByPaymentType(PaymentType paymentType) throws PaymentNotFoundException {
        Optional<Payment> paymentOptional = paymentRepository.findByPaymentType(paymentType);

        if (paymentOptional.isEmpty()){
            throw new PaymentNotFoundException(paymentType);
        }
        return paymentOptional.get();
    }

    @Override
    public Payment findByCardNumber(String cardNumber) throws PaymentNotFoundException {
        Optional<Payment> paymentOptional = paymentRepository.findByCardNumber(cardNumber);

        if (paymentOptional.isEmpty()){
            throw new PaymentNotFoundException(cardNumber);
        }
        return paymentOptional.get();
    }

    @Override
    public Payment findByBank(String bank) throws PaymentNotFoundException {
        Optional<Payment> paymentOptional = paymentRepository.findByBank(bank);

        if (paymentOptional.isEmpty()){
            throw new PaymentNotFoundException(bank);
        }
        return paymentOptional.get();
    }

    @Override
    public void createPayment(Payment payment) {
        payment.setActive(true);
        paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Payment payment) throws PaymentNotFoundException {
        if(!paymentRepository.existsById(payment.getId())) {
            throw new PaymentNotFoundException(payment.getId());
        }
        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public void deletePaymentById(Long id) throws PaymentNotFoundException {
        Optional<Payment> paymentOptional = Optional.ofNullable(findById(id));

        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException(id);
        } else {
            Payment payment = paymentOptional.get();
            payment.setActive(false);
            paymentRepository.saveAndFlush(payment);
        }
    }

    @Override
    public void restorePaymentById(Long id) throws PaymentNotFoundException {
        Optional<Payment> paymentOptional = Optional.ofNullable(findById(id));

        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException(id);
        } else {
            Payment payment = paymentOptional.get();
            payment.setActive(true);
            paymentRepository.saveAndFlush(payment);
        }

    }
}
