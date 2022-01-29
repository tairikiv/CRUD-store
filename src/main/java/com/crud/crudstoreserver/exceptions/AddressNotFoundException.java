package com.crud.crudstoreserver.exceptions;

public class AddressNotFoundException extends Exception{
    public AddressNotFoundException(Long id) {
        super("Address (id:" + id + ") not found!");
    }
}
