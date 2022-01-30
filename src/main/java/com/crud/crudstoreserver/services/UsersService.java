package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.CartNotFoundException;
import com.crud.crudstoreserver.exceptions.UsersNotFoundException;
import com.crud.crudstoreserver.models.Users;

import java.util.List;

public interface UsersService {

    List<Users> findAllUsers();

    Users findById(Long id) throws UsersNotFoundException;

    void createUsers (Users user);

    void updateUsers(Users user) throws UsersNotFoundException;

    void deleteUsersById(Long id) throws UsersNotFoundException, CartNotFoundException;

    void restoreUsersById(Long id) throws UsersNotFoundException, CartNotFoundException;

}
