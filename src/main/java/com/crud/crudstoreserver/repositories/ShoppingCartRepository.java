package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.ShoppingCart;
import com.crud.crudstoreserver.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findCartByPerson(Person person);
}
