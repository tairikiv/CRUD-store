package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.AddressNotFoundException;
import com.crud.crudstoreserver.exceptions.BankAccountNotFoundException;
import com.crud.crudstoreserver.exceptions.CartNotFoundException;
import com.crud.crudstoreserver.exceptions.PaymentFailedException;
import com.crud.crudstoreserver.models.*;
import com.crud.crudstoreserver.services.AddressService;
import com.crud.crudstoreserver.services.CartService;
import com.crud.crudstoreserver.services.OrderService;
import com.crud.crudstoreserver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getCartByUser(@RequestBody Users user) throws CartNotFoundException {
        cartService.findCartByUser(user);

        try {
            cartService.findCartByUser(user);
        } catch (CartNotFoundException cartNotFoundException) {
            return new ResponseEntity<>(cartNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @GetMapping("/checkout")
    public ResponseEntity<?> checkoutCartByUser(@RequestBody Users user) {
        try {
            Cart userCart = cartService.findCartByUser(user);
            Payment payment = paymentService.initiatePayment(user, userCart.getTotalCost());
            Address address = addressService.getDefaultAddressByUser(user);
            Order order = new Order();
            order.setUser(user);
            order.setAddress(address);
            order.setOrderDate(LocalDate.now());
            order.setOrderItems(userCart.getOrderItems());
            order.setOrderStatus(PaymentStatus.PENDING.equals(payment.getPaymentStatus()) ? OrderStatus.PENDING : OrderStatus.PAID);
            order.setPayment(payment);
            orderService.createOrder(order);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CartNotFoundException | BankAccountNotFoundException | AddressNotFoundException | PaymentFailedException exception) {
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
