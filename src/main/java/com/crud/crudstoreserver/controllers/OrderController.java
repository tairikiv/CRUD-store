package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.OrderNotFoundException;
import com.crud.crudstoreserver.models.Order;
import com.crud.crudstoreserver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> showAllOrders( ) {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) throws OrderNotFoundException{
        orderService.findById(id);

        try{
            orderService.findById(id);
        } catch (OrderNotFoundException orderNotFoundException) {
            return new ResponseEntity<>(orderNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody @Valid Order order) throws OrderNotFoundException {
        orderService.updateOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(order, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) throws OrderNotFoundException {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreOrder(@PathVariable("id") Long id) throws OrderNotFoundException {
        orderService.restoreOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody @Valid Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
