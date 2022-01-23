package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.Role;
import com.crud.crudstoreserver.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByEmail(String email);

    Optional<Users> findUsersByRole(Role role);
}
