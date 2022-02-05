package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.PurchaseOrderNotFoundException;
import com.crud.crudstoreserver.models.PurchaseOrder;
import com.crud.crudstoreserver.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> showAllOrders( ) {
        return purchaseOrderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) throws PurchaseOrderNotFoundException {
        purchaseOrderService.findById(id);

        try{
            purchaseOrderService.findById(id);
        } catch (PurchaseOrderNotFoundException purchaseOrderNotFoundException) {
            return new ResponseEntity<>(purchaseOrderNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody @Valid PurchaseOrder purchaseOrder) throws PurchaseOrderNotFoundException {
        purchaseOrderService.updateOrder(purchaseOrder);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(purchaseOrder, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) throws PurchaseOrderNotFoundException {
        purchaseOrderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreOrder(@PathVariable("id") Long id) throws PurchaseOrderNotFoundException {
        purchaseOrderService.restoreOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody @Valid PurchaseOrder purchaseOrder) {
        purchaseOrderService.createOrder(purchaseOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
