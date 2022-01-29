package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.ProductType;

import java.math.BigDecimal;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(Long id) {
        super("Product (id:" + id + " ) not found!");
    }

    public ProductNotFoundException(String name) {
        super("Product (name:" + name + " ) not found!");
    }

    public ProductNotFoundException(BigDecimal price) {
        super("Product (price:" + price + " ) not found!");
    }

    public ProductNotFoundException(ProductType productType) {
        super("Product (productType:" + productType + " ) not found!");
    }

    public ProductNotFoundException(int size) {
        super("Product (size:" + size + " ) not found!");
    }
}
