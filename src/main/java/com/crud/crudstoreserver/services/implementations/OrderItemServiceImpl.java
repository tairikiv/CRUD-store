package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.repositories.OrderItemRepository;
import com.crud.crudstoreserver.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findOrderItemById(Long id) {
        return orderItemRepository.findOrderItemById(id);
    }

    @Override
    public Optional<OrderItem> findOrderByUserProduct(Product product) {
        return orderItemRepository.findOrderItemByProduct(product);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        if(orderItem == null || !orderItemRepository.existsById(orderItem.getId())) {
            throw new RuntimeException("OrderItem not found!");
        }
        orderItemRepository.saveAndFlush(orderItem);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        Optional<OrderItem> orderItemOptional = findOrderItemById(id);

        if(orderItemOptional.isEmpty()) {
            throw new RuntimeException("OrderItem not found!");
        } else {
            OrderItem orderItem = orderItemOptional.get();
            orderItem.setActive(false);
            orderItemRepository.saveAndFlush(orderItem);
        }
    }

    @Override
    public void restoreOrderItemById(Long id) {
        Optional<OrderItem> orderItemOptional = findOrderItemById(id);

        if(orderItemOptional.isEmpty()) {
            throw new RuntimeException("OrderItem not found!");
        } else {
            OrderItem orderItem = orderItemOptional.get();
            orderItem.setActive(true);
            orderItemRepository.saveAndFlush(orderItem);
        }
    }
}
