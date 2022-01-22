package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressByCity(String city);

    Optional<Address> findAddressByCountyState(String countyState);
}
