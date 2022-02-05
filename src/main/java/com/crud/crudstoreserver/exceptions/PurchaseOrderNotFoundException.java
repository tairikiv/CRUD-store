package com.crud.crudstoreserver.exceptions;

public class PurchaseOrderNotFoundException extends Exception {
    public PurchaseOrderNotFoundException(Long id) {
        super("Purchase Order Item (id:" + id + ") not found!");
    }
}
