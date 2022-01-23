package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUserAccount(int userAccount);
}
