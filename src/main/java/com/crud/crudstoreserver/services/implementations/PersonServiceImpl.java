package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.ShoppingCartNotFoundException;
import com.crud.crudstoreserver.exceptions.PersonNotFoundException;
import com.crud.crudstoreserver.models.Person;
import com.crud.crudstoreserver.repositories.PersonRepository;
import com.crud.crudstoreserver.services.AddressService;
import com.crud.crudstoreserver.services.BankAccountService;
import com.crud.crudstoreserver.services.ShoppingCartService;
import com.crud.crudstoreserver.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) throws PersonNotFoundException {
        Optional<Person> usersOptional = personRepository.findById(id);

        if (usersOptional.isEmpty()) {
            throw new PersonNotFoundException(id);
        }
        return usersOptional.get();
    }

    @Override
    public void createPersons(Person person) {
        person.setAddresses(addressService.createBulkAddresses(person.getAddresses()));
        person.setBankAccounts(bankAccountService.createBulkBankAccounts(person.getBankAccounts()));
        person.setActive(true);
        personRepository.save(person);

        shoppingCartService.createCartByPerson(person);
    }

    @Override
    public void updatePersons(Person person) throws PersonNotFoundException {
        if(!personRepository.existsById(person.getId())) {
            throw new PersonNotFoundException(person.getId());
        }
        personRepository.saveAndFlush(person);
    }

    @Override
    public void deletePersonsById(Long id) throws PersonNotFoundException, ShoppingCartNotFoundException {
        Optional<Person> usersOptional = Optional.ofNullable(findById(id));

        if (usersOptional.isEmpty()) {
            throw new PersonNotFoundException(id);
        } else {
            Person person = usersOptional.get();
            person.setActive(false);
            personRepository.saveAndFlush(person);
            shoppingCartService.deleteCartByPersons(person);
        }
    }

    @Override
    public void restorePersonsById(Long id) throws PersonNotFoundException, ShoppingCartNotFoundException {
        Optional<Person> usersOptional = Optional.ofNullable(findById(id));

        if (usersOptional.isEmpty()) {
            throw new PersonNotFoundException(id);
        } else {
            Person person = usersOptional.get();
            person.setActive(true);
            personRepository.saveAndFlush(person);
            shoppingCartService.restoreCartByPerson(person);
        }
    }
}
