package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAllAddresses();

    void createAddress(Address address);

    Optional<Address> findAddressByCity(String city);

    Optional<Address> findAddressByCountyState(String countyState);
}
