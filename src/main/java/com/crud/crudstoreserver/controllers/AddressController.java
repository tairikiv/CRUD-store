package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.models.Address;
import com.crud.crudstoreserver.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.findAllAddresses();
    }

    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody @Valid Address address){
        addressService.createAddress(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
