package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.ShoppingCartNotFoundException;
import com.crud.crudstoreserver.exceptions.PersonNotFoundException;
import com.crud.crudstoreserver.models.Person;
import com.crud.crudstoreserver.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.findAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonsById(@PathVariable Long id) throws PersonNotFoundException {
        try {
              Person person = personService.findById(id);
              return new ResponseEntity<>(person, HttpStatus.FOUND);
        } catch (PersonNotFoundException personNotFoundException) {
            return new ResponseEntity<>(personNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<?> addPersons(@RequestBody @Valid Person person){
        personService.createPersons(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Person> updatePersons(@RequestBody @Valid Person person) throws PersonNotFoundException {
        personService.updatePersons(person);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(person, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deletePersons(@PathVariable("id") Long id) throws PersonNotFoundException, ShoppingCartNotFoundException {
        personService.deletePersonsById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restorePersons(@PathVariable("id") Long id) throws PersonNotFoundException, ShoppingCartNotFoundException {
        personService.restorePersonsById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
