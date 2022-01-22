package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.models.Order;
import com.crud.crudstoreserver.repositories.OrderRepository;
import com.crud.crudstoreserver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
   @Autowired
   private OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public Optional<Order> findOrderByUserAccount(int userAccount) {
        return orderRepository.findOrderByUserAccount(userAccount);
    }

    @Override
    public void updateOrder(Order order) {
        if(order == null || !orderRepository.existsById(order.getId())) {
            throw new RuntimeException("Order not found!");
        }
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        Optional<Order> orderOptional = findOrderById(id);

        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found!");
        } else {
            Order order = orderOptional.get();
            order.setActive(false);
            orderRepository.saveAndFlush(order);
        }

    }

    @Override
    public void restoreOrderById(Long id) {
        Optional<Order> orderOptional = findOrderById(id);

        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found!");
        } else {
            Order order = orderOptional.get();
            order.setActive(true);
            orderRepository.saveAndFlush(order);
        }
    }
}
