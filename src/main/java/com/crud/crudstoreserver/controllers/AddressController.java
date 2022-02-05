package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.AddressNotFoundException;
import com.crud.crudstoreserver.models.Address;
import com.crud.crudstoreserver.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        try{
            Address address = addressService.findById(id);
            return new ResponseEntity<>(address, HttpStatus.FOUND);
        } catch (AddressNotFoundException addressNotFoundException ) {
            return new ResponseEntity<>(addressNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody @Valid Address address){
        addressService.createAddress(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@RequestBody @Valid Address address) throws AddressNotFoundException {
        addressService.updateAddress(address);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(address, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id) throws AddressNotFoundException {
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreAddress(@PathVariable("id") Long id) throws AddressNotFoundException {
        addressService.restoreAddressById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
