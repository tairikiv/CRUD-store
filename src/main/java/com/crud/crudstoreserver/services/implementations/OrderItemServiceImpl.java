package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.OrderItemNotFoundException;
import com.crud.crudstoreserver.models.Order;
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
    public OrderItem findById(Long id) throws OrderItemNotFoundException {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);

        if(orderItemOptional.isEmpty()) {
            throw new OrderItemNotFoundException(id);
        }

        return orderItemOptional.get();
    }

    @Override
    public OrderItem findByProduct(Product product) throws OrderItemNotFoundException {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findByProduct(product);

        if(orderItemOptional.isEmpty()) {
            throw new OrderItemNotFoundException(product);
        }

        return orderItemOptional.get();
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) throws OrderItemNotFoundException {
        if(!orderItemRepository.existsById(orderItem.getId())) {
            throw new OrderItemNotFoundException(orderItem.getId());
        }

        orderItemRepository.saveAndFlush(orderItem);
    }

    @Override
    public void deleteOrderItemById(Long id) throws OrderItemNotFoundException {
        Optional<OrderItem> orderItemOptional = Optional.ofNullable(findById(id));

        if(orderItemOptional.isEmpty()) {
            throw new OrderItemNotFoundException(id);
        } else {
            OrderItem orderItem = orderItemOptional.get();
            orderItem.setActive(false);
            orderItemRepository.saveAndFlush(orderItem);
        }
    }

    @Override
    public void restoreOrderItemById(Long id) throws OrderItemNotFoundException {
        Optional<OrderItem> orderItemOptional = Optional.ofNullable(findById(id));

        if(orderItemOptional.isEmpty()) {
            throw new OrderItemNotFoundException(id);
        } else {
            OrderItem orderItem = orderItemOptional.get();
            orderItem.setActive(true);
            orderItemRepository.saveAndFlush(orderItem);
        }
    }
}
