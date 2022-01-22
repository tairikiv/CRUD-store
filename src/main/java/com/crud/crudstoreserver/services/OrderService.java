package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAllOrders();

    Optional<Order> findOrderById(Long id);

    Optional<Order> findOrderByUserAccount(int userAccount);

    void updateOrder(Order order);

    void deleteOrderById(Long id);

    void restoreOrderById(Long id);


}
