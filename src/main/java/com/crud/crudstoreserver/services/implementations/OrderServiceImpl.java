package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.OrderNotFoundException;
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
    public Order findById(Long id) throws OrderNotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        return orderOptional.get();
    }


    @Override
    public void updateOrder(Order order) throws OrderNotFoundException {
        if(!orderRepository.existsById(order.getId())) {
            throw new OrderNotFoundException(order.getId());
        }
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrderById(Long id) throws OrderNotFoundException {
        Optional<Order> orderOptional = Optional.ofNullable(findById(id));

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException(id);
        } else {
            Order order = orderOptional.get();
            order.setActive(false);
            orderRepository.saveAndFlush(order);
        }
    }

    @Override
    public void restoreOrderById(Long id) throws OrderNotFoundException{
        Optional<Order> orderOptional = Optional.ofNullable(findById(id));

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException(id);
        } else {
            Order order = orderOptional.get();
            order.setActive(true);
            orderRepository.saveAndFlush(order);
        }
    }
}
