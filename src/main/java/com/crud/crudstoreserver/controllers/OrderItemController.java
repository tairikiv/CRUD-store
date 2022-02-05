package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.CartNotFoundException;
import com.crud.crudstoreserver.exceptions.OrderItemNotFoundException;
import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Users;
import com.crud.crudstoreserver.services.CartService;
import com.crud.crudstoreserver.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<OrderItem> showAllOrderItems() {
        return orderItemService.findAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Long id) throws OrderItemNotFoundException {
        orderItemService.findById(id);

        try{
            orderItemService.findById(id);
        } catch (OrderItemNotFoundException orderItemNotFoundException) {
            return new ResponseEntity<>(orderItemNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( HttpStatus.FOUND);
    }

    @GetMapping
    public List<OrderItem> showAllActiveOrderItemsByUser(Users user) {
        return  orderItemService.findAllActiveOrderItemsByUser(user);
    }

    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestBody @Valid OrderItem orderItem) {
        orderItemService.createOrderItem(orderItem);

        try {
            cartService.updateCartByUser(orderItem.getUser());
        } catch (CartNotFoundException cartNotFoundException){
            return new ResponseEntity<>(cartNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
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
