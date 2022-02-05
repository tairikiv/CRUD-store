package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.PurchaseOrderNotFoundException;
import com.crud.crudstoreserver.models.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrder> findAllOrders();

    PurchaseOrder findById(Long id) throws PurchaseOrderNotFoundException;

    PurchaseOrder createOrder(PurchaseOrder purchaseOrder);

    void updateOrder(PurchaseOrder purchaseOrder) throws PurchaseOrderNotFoundException;

    void deleteOrderById(Long id) throws PurchaseOrderNotFoundException;

    void restoreOrderById(Long id) throws PurchaseOrderNotFoundException;


}
