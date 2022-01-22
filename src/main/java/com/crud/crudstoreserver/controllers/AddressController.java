package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.models.Address;
import com.crud.crudstoreserver.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    public List<Address> getAllAddresses() {
        return addressService.findAllAddresses();
    }
}
