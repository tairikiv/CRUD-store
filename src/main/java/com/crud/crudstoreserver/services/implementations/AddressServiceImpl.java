package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.AddressNotFoundException;
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
    public Address findById(Long id) throws AddressNotFoundException {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isEmpty()) {
            throw new AddressNotFoundException(id);
        }
        return addressOptional.get();
    }

    @Override
    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Address address) throws AddressNotFoundException {
        if(!addressRepository.existsById(address.getId())) {
            throw new AddressNotFoundException(address.getId());
        }
        addressRepository.saveAndFlush(address);
    }

    @Override
    public void deleteAddressById(Long id) throws AddressNotFoundException {
        Optional<Address> addressOptional = Optional.ofNullable(findById(id));

        if (addressOptional.isEmpty()) {
            throw new AddressNotFoundException(id);
        } else {
            Address address = addressOptional.get();
            address.setActive(false);
            addressRepository.saveAndFlush(address);
        }
    }

    @Override
    public void restoreAddressById(Long id) throws AddressNotFoundException {
        Optional<Address> addressOptional = Optional.ofNullable(findById(id));

        if (addressOptional.isEmpty()){
            throw new AddressNotFoundException(id);
        }else {
            Address address = addressOptional.get();
            address.setActive(true);
            addressRepository.saveAndFlush(address);
        }

    }

}
