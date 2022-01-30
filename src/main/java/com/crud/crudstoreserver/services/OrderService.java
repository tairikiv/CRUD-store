package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.OrderNotFoundException;
import com.crud.crudstoreserver.models.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrders();

    Order findById(Long id) throws OrderNotFoundException;

    Order createOrder(Order order);

    void updateOrder(Order order) throws OrderNotFoundException;

    void deleteOrderById(Long id) throws OrderNotFoundException;

    void restoreOrderById(Long id) throws OrderNotFoundException;


}
