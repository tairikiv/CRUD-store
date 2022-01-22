package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Product;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    List<OrderItem> findAllOrderItems();

    Optional<OrderItem> findOrderItemById(Long id);

    Optional<OrderItem> findOrderByUserProduct(Product product);

    void updateOrderItem(OrderItem orderItem);

    void deleteOrderItemById(Long id);

    void restoreOrderItemById(Long id);
}
