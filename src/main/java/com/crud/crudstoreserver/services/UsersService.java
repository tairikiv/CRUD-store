package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.UsersNotFoundException;
import com.crud.crudstoreserver.models.Role;
import com.crud.crudstoreserver.models.Users;

import java.util.List;

public interface UsersService {

    List<Users> findAllUsers();

    Users findById(Long id) throws UsersNotFoundException;

    Users findUsersByEmail(String email);

    Users findUsersByRole(Role role);

    void createUsers (Users users);

    void updateUsers(Users users) throws UsersNotFoundException;

    void deleteUsersById(Long id) throws UsersNotFoundException;

    void restoreUsersById(Long id) throws UsersNotFoundException;
}
