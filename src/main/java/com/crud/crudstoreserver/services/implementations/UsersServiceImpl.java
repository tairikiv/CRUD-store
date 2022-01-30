package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.CartNotFoundException;
import com.crud.crudstoreserver.exceptions.UsersNotFoundException;
import com.crud.crudstoreserver.models.Users;
import com.crud.crudstoreserver.repositories.UsersRepository;
import com.crud.crudstoreserver.services.AddressService;
import com.crud.crudstoreserver.services.BankAccountService;
import com.crud.crudstoreserver.services.CartService;
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

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private BankAccountService bankAccountService;

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
    public void createUsers(Users user) {
        user.setAddresses(addressService.createBulkAddresses(user.getAddresses()));
        user.setBankAccounts(bankAccountService.createBulkBankAccounts(user.getBankAccounts()));
        user.setActive(true);
        usersRepository.save(user);

        cartService.createCartByUser(user);
    }

    @Override
    public void updateUsers(Users user) throws UsersNotFoundException {
        if(!usersRepository.existsById(user.getId())) {
            throw new UsersNotFoundException(user.getId());
        }
        usersRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUsersById(Long id) throws UsersNotFoundException, CartNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable(findById(id));

        if (usersOptional.isEmpty()) {
            throw new UsersNotFoundException(id);
        } else {
            Users user = usersOptional.get();
            user.setActive(false);
            usersRepository.saveAndFlush(user);
            cartService.deleteCartByUsers(user);
        }
    }

    @Override
    public void restoreUsersById(Long id) throws UsersNotFoundException, CartNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable(findById(id));

        if (usersOptional.isEmpty()) {
            throw new UsersNotFoundException(id);
        } else {
            Users user = usersOptional.get();
            user.setActive(true);
            usersRepository.saveAndFlush(user);
            cartService.restoreCartByUser(user);
        }
    }
}
