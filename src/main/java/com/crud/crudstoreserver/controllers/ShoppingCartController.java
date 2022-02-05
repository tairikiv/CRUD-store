package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.AddressNotFoundException;
import com.crud.crudstoreserver.exceptions.BankAccountNotFoundException;
import com.crud.crudstoreserver.exceptions.ShoppingCartNotFoundException;
import com.crud.crudstoreserver.exceptions.PaymentFailedException;
import com.crud.crudstoreserver.models.*;
import com.crud.crudstoreserver.services.AddressService;
import com.crud.crudstoreserver.services.ShoppingCartService;
import com.crud.crudstoreserver.services.PurchaseOrderService;
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
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getCartByPerson(@RequestBody Person person) throws ShoppingCartNotFoundException {
        shoppingCartService.findCartByPerson(person);

        try {
            shoppingCartService.findCartByPerson(person);
        } catch (ShoppingCartNotFoundException shoppingCartNotFoundException) {
            return new ResponseEntity<>(shoppingCartNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @GetMapping("/checkout")
    public ResponseEntity<?> checkoutCartByPerson(@RequestBody Person person) {
        try {
            ShoppingCart userShoppingCart = shoppingCartService.findCartByPerson(person);
            Payment payment = paymentService.initiatePayment(person, userShoppingCart.getTotalCost());
            Address address = addressService.getDefaultAddressByPerson(person);
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setPerson(person);
            purchaseOrder.setAddress(address);
            purchaseOrder.setOrderDate(LocalDate.now());
            purchaseOrder.setPurchaseOrderItems(userShoppingCart.getPurchaseOrderItems());
            purchaseOrder.setPurchaseOrderStatus(PaymentStatus.PENDING.equals(payment.getPaymentStatus()) ? PurchaseOrderStatus.PENDING : PurchaseOrderStatus.PAID);
            purchaseOrder.setPayment(payment);
            purchaseOrderService.createOrder(purchaseOrder);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ShoppingCartNotFoundException | BankAccountNotFoundException | AddressNotFoundException | PaymentFailedException exception) {
            return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
