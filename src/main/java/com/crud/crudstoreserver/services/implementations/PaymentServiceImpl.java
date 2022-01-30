package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.BankAccountNotFoundException;
import com.crud.crudstoreserver.exceptions.PaymentFailedException;
import com.crud.crudstoreserver.exceptions.PaymentNotFoundException;
import com.crud.crudstoreserver.models.BankAccount;
import com.crud.crudstoreserver.models.Payment;
import com.crud.crudstoreserver.models.PaymentStatus;
import com.crud.crudstoreserver.models.Users;
import com.crud.crudstoreserver.repositories.PaymentRepository;
import com.crud.crudstoreserver.services.BankAccountService;
import com.crud.crudstoreserver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BankAccountService bankAccountService;


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
    public Payment initiatePayment(Users user, BigDecimal totalSum) throws BankAccountNotFoundException, PaymentFailedException {
        BankAccount defaultBankAccount = bankAccountService.getDefaultBankAccountByUser(user);
        Payment payment = new Payment();
        payment.setBankAccount(defaultBankAccount);
        payment.setTotalSum(totalSum);
        debitPayment(payment);

        if(PaymentStatus.FAILED.equals(payment.getPaymentStatus())){
            throw new PaymentFailedException(payment);
        }

        return createPayment(payment);
    }

    @Override
    public void debitPayment(Payment payment) {
        payment.setPaymentStatus(PaymentStatus.DONE);
    }

    @Override
    public Payment createPayment(Payment payment) {
        payment.setActive(true);
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Payment payment) throws PaymentNotFoundException {
        if (!paymentRepository.existsById(payment.getId())) {
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
