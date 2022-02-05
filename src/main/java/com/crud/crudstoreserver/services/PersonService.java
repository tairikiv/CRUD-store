package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.ShoppingCartNotFoundException;
import com.crud.crudstoreserver.exceptions.PersonNotFoundException;
import com.crud.crudstoreserver.models.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAllPersons();

    Person findById(Long id) throws PersonNotFoundException;

    void createPersons (Person person);

    void updatePersons(Person person) throws PersonNotFoundException;

    void deletePersonsById(Long id) throws PersonNotFoundException, ShoppingCartNotFoundException;

    void restorePersonsById(Long id) throws PersonNotFoundException, ShoppingCartNotFoundException;

}
