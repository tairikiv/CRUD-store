package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findOrderItemById(Long id);

    Optional<OrderItem> findOrderItemByProduct(Product product);
}
