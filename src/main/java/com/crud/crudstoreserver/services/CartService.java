package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.CartNotFoundException;
import com.crud.crudstoreserver.models.Cart;
import com.crud.crudstoreserver.models.Users;

public interface CartService {

    Cart findCartByUser(Users user) throws CartNotFoundException;
    void createCartByUser(Users user);

    Cart updateCartByUser(Users user) throws CartNotFoundException;


    void restoreCartByUser(Users user) throws CartNotFoundException;

    void deleteCartByUsers(Users user) throws CartNotFoundException;
}
