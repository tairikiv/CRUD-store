package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByProduct(Product product);

    List<OrderItem> findAllByUserAndActiveIsTrue(Users user);
}
