package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.ShoppingCartNotFoundException;
import com.crud.crudstoreserver.models.ShoppingCart;
import com.crud.crudstoreserver.models.Person;

public interface ShoppingCartService {

    ShoppingCart findCartByPerson(Person person) throws ShoppingCartNotFoundException;
    void createCartByPerson(Person person);

    ShoppingCart updateCartByPerson(Person person) throws ShoppingCartNotFoundException;


    void restoreCartByPerson(Person person) throws ShoppingCartNotFoundException;

    void deleteCartByPersons(Person person) throws ShoppingCartNotFoundException;
}
