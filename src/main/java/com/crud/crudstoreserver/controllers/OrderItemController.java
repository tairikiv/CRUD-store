package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.OrderItemNotFoundException;
import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;


    @GetMapping
    public List<OrderItem> showAllOrderItems() {
        return orderItemService.findAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) throws OrderItemNotFoundException {
        Optional<OrderItem> orderItemOptional = Optional.ofNullable(orderItemService.findById(id));

        if (orderItemOptional.isEmpty()) {
            throw new OrderItemNotFoundException(id);
        }
        return new ResponseEntity<>(orderItemOptional.get(), HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody @Valid OrderItem orderItem) throws OrderItemNotFoundException {
        orderItemService.updateOrderItem(orderItem);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(orderItem, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable("id") Long id) throws OrderItemNotFoundException {
        orderItemService.deleteOrderItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreOrderItem(@PathVariable("id") Long id) throws OrderItemNotFoundException {
        orderItemService.restoreOrderItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
