package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.PaymentReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentReferenceRepository extends JpaRepository<PaymentReference, Long> {
}
