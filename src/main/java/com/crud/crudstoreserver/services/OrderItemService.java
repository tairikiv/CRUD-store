package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.OrderItemNotFoundException;
import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.Users;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAllOrderItems();

    OrderItem findById(Long id) throws OrderItemNotFoundException;

    OrderItem findByProduct(Product product) throws OrderItemNotFoundException;

    void updateOrderItem(OrderItem orderItem) throws OrderItemNotFoundException;

    void deleteOrderItemById(Long id) throws OrderItemNotFoundException;

    void restoreOrderItemById(Long id) throws OrderItemNotFoundException;

    List<OrderItem> findAllActiveOrderItemsByUser(Users user);

    void createOrderItem(OrderItem orderItem);
}
