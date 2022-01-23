package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.UsersNotFoundException;
import com.crud.crudstoreserver.models.Role;
import com.crud.crudstoreserver.models.Users;
import com.crud.crudstoreserver.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.findAllUsers();
    }

    @GetMapping
    public ResponseEntity<Users> getUsersById(@PathVariable Long id) throws UsersNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable(usersService.findById(id));

        if (usersOptional.isEmpty()) {
            throw new UsersNotFoundException(id);
        }
        return new ResponseEntity<>(usersOptional.get(), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<Users> getUsersByEmail(@PathVariable String email) throws UsersNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable(usersService.findUsersByEmail(email));

        if (usersOptional.isEmpty()) {
            throw new UsersNotFoundException(email);
        }
        return new ResponseEntity<>(usersOptional.get(), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<Users> getUsersByRole(@PathVariable Role role) {
        Optional<Users> usersOptional = Optional.ofNullable(usersService.findUsersByRole(role));

        return new ResponseEntity<>(usersOptional.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addUsers(@RequestBody @Valid Users users){
        usersService.createUsers(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Users> updateUsers(@RequestBody @Valid Users users) throws UsersNotFoundException {
        usersService.updateUsers(users);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable("id") Long id) throws UsersNotFoundException {
        usersService.deleteUsersById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> restoreUsers(@PathVariable("id") Long id) throws UsersNotFoundException {
        usersService.restoreUsersById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
