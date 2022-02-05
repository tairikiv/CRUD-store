package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.PurchaseOrderItem;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {
    Optional<PurchaseOrderItem> findByProduct(Product product);

    List<PurchaseOrderItem> findAllByPerson(Person person);
}
