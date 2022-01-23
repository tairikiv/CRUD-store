package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.OrderItemNotFoundException;
import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Product;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAllOrderItems();

    OrderItem findById(Long id) throws OrderItemNotFoundException;

    OrderItem findByProduct(Product product);

    void updateOrderItem(OrderItem orderItem) throws OrderItemNotFoundException;

    void deleteOrderItemById(Long id) throws OrderItemNotFoundException;

    void restoreOrderItemById(Long id) throws OrderItemNotFoundException;
}
