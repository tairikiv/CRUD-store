package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.CartNotFoundException;
import com.crud.crudstoreserver.exceptions.UsersNotFoundException;
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

@RestController
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable Long id) throws UsersNotFoundException {
        usersService.findById(id);

        try {
              usersService.findById(id);
        } catch (UsersNotFoundException usersNotFoundException) {
            return new ResponseEntity<>(usersNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }


    @PostMapping
    public ResponseEntity<?> addUsers(@RequestBody @Valid Users user){
        usersService.createUsers(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Users> updateUsers(@RequestBody @Valid Users user) throws UsersNotFoundException {
        usersService.updateUsers(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable("id") Long id) throws UsersNotFoundException, CartNotFoundException {
        usersService.deleteUsersById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreUsers(@PathVariable("id") Long id) throws UsersNotFoundException, CartNotFoundException {
        usersService.restoreUsersById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
