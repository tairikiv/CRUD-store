package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.models.Address;
import com.crud.crudstoreserver.repositories.AddressRepository;
import com.crud.crudstoreserver.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

   @Autowired
   private AddressRepository addressRepository;


    @Override
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Optional<Address> findAddressByCity(String city) {
        return addressRepository.findAddressByCity(city);
    }

    @Override
    public Optional<Address> findAddressByCountyState(String countyState) {
        return addressRepository.findAddressByCountyState(countyState);
    }
}
