package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.Cart;
import com.crud.crudstoreserver.models.Order;
import com.crud.crudstoreserver.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByUser(Users user);
}
