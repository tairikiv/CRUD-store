package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.PurchaseOrder;
import com.crud.crudstoreserver.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    Optional<PurchaseOrder> findByPerson(Person person);
}
