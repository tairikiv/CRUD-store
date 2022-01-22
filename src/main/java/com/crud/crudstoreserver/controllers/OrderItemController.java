package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.services.OrderItemService;
import com.crud.crudstoreserver.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;
}
