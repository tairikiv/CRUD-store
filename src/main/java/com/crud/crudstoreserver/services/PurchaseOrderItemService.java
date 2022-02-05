package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.PurchaseOrderItemNotFoundException;
import com.crud.crudstoreserver.models.PurchaseOrderItem;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.Person;

import java.util.List;

public interface PurchaseOrderItemService {

    List<PurchaseOrderItem> findAllOrderItems();

    PurchaseOrderItem findById(Long id) throws PurchaseOrderItemNotFoundException;

    PurchaseOrderItem findByProduct(Product product) throws PurchaseOrderItemNotFoundException;

    void updateOrderItem(PurchaseOrderItem purchaseOrderItem) throws PurchaseOrderItemNotFoundException;

    void deleteOrderItemById(Long id) throws PurchaseOrderItemNotFoundException;

    void restoreOrderItemById(Long id) throws PurchaseOrderItemNotFoundException;

    List<PurchaseOrderItem> findAllActiveOrderItemsByPerson(Person person);

    void createOrderItem(PurchaseOrderItem purchaseOrderItem);
}
