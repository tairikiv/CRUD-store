package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.Payment;
import com.crud.crudstoreserver.models.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentType(PaymentType paymentType);

    Optional<Payment> findByCardNumber(String cardNumber);

    Optional<Payment> findByBank(String bank);
}

