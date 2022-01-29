package com.crud.crudstoreserver.exceptions;


public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(Long id) {
        super("Product (id:" + id + " ) not found!");
    }

}
