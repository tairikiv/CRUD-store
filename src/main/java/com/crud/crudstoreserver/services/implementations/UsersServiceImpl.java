package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.OrderNotFoundException;
import com.crud.crudstoreserver.exceptions.UsersNotFoundException;
import com.crud.crudstoreserver.models.Order;
import com.crud.crudstoreserver.models.Role;
import com.crud.crudstoreserver.models.Users;
import com.crud.crudstoreserver.repositories.UsersRepository;
import com.crud.crudstoreserver.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) throws UsersNotFoundException {
        Optional<Users> usersOptional = usersRepository.findById(id);

        if (usersOptional.isEmpty()) {
            throw new UsersNotFoundException(id);
        }
        return usersOptional.get();
    }

    @Override
    public Users findUsersByEmail(String email) {
        Optional<Users> usersOptional = usersRepository.findUsersByEmail(email);

        return usersOptional.get();
    }

    @Override
    public Users findUsersByRole(Role role) {
        Optional<Users> usersOptional = usersRepository.findUsersByRole(role);

        return usersOptional.get();
    }

    @Override
    public void createUsers(Users users) {
        users.setActive(true);
        usersRepository.save(users);
    }

    @Override
    public void updateUsers(Users users) throws UsersNotFoundException {
        if(!usersRepository.existsById(users.getId())) {
            throw new UsersNotFoundException(users.getId());
        }
        usersRepository.saveAndFlush(users);
    }

    @Override
    public void deleteUsersById(Long id) throws UsersNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable(findById(id));

        if (usersOptional.isEmpty()) {
            throw new UsersNotFoundException(id);
        } else {
            Users users = usersOptional.get();
            users.setActive(false);
            usersRepository.saveAndFlush(users);
        }
    }

    @Override
    public void restoreUsersById(Long id) throws UsersNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable(findById(id));

        if (usersOptional.isEmpty()) {
            throw new UsersNotFoundException(id);
        } else {
            Users users = usersOptional.get();
            users.setActive(true);
            usersRepository.saveAndFlush(users);
        }
    }
}
