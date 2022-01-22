package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.services.AddressService;
import com.crud.crudstoreserver.services.OrderItemService;
import com.crud.crudstoreserver.services.OrderService;
import com.crud.crudstoreserver.services.PaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private PaymentReferenceService paymentReferenceService;
}
