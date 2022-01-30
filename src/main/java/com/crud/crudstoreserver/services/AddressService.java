package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.AddressNotFoundException;
import com.crud.crudstoreserver.models.Address;
import com.crud.crudstoreserver.models.Users;

import java.util.List;

public interface AddressService {

    List<Address> findAllAddresses();

    Address findById(Long id) throws AddressNotFoundException;

    Address createAddress(Address address);

    List<Address> createBulkAddresses(List<Address> addresses);

    void updateAddress(Address address) throws AddressNotFoundException;

    void deleteAddressById(Long id) throws AddressNotFoundException;

    void restoreAddressById(Long id) throws AddressNotFoundException;

    Address getDefaultAddressByUser(Users user) throws AddressNotFoundException;

}
