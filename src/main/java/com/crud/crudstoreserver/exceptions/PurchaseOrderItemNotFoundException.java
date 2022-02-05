package com.crud.crudstoreserver.exceptions;

import com.crud.crudstoreserver.models.Product;

public class PurchaseOrderItemNotFoundException extends Exception{
    public PurchaseOrderItemNotFoundException(Long id) {
        super("Purchase Order Item (id:" + id + ") not found!");
    }

    public PurchaseOrderItemNotFoundException(Product product) {
        super("Purchase Order Item (id: " + product + ") not found!" );

    }
}
