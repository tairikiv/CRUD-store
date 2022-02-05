package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.ShoppingCartNotFoundException;
import com.crud.crudstoreserver.exceptions.PurchaseOrderItemNotFoundException;
import com.crud.crudstoreserver.models.PurchaseOrderItem;
import com.crud.crudstoreserver.models.Person;
import com.crud.crudstoreserver.services.ShoppingCartService;
import com.crud.crudstoreserver.services.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order-item")
public class PurchaseOrderItemController {
    @Autowired
    private PurchaseOrderItemService purchaseOrderItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<PurchaseOrderItem> showAllOrderItems() {
        return purchaseOrderItemService.findAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Long id) throws PurchaseOrderItemNotFoundException {
        try{
            PurchaseOrderItem purchaseOrderItem = purchaseOrderItemService.findById(id);
            return new ResponseEntity<>(purchaseOrderItem, HttpStatus.FOUND);
        } catch (PurchaseOrderItemNotFoundException purchaseOrderItemNotFoundException) {
            return new ResponseEntity<>(purchaseOrderItemNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/person")
    public List<PurchaseOrderItem> showAllActiveOrderItemsByPerson(Person person) {
        return  purchaseOrderItemService.findAllActiveOrderItemsByPerson(person);
    }

    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestBody @Valid PurchaseOrderItem purchaseOrderItem) {
        purchaseOrderItemService.createOrderItem(purchaseOrderItem);

        try {
            shoppingCartService.updateCartByPerson(purchaseOrderItem.getPerson());
        } catch (ShoppingCartNotFoundException shoppingCartNotFoundException){
            return new ResponseEntity<>(shoppingCartNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PurchaseOrderItem> updateOrderItem(@RequestBody @Valid PurchaseOrderItem purchaseOrderItem) throws PurchaseOrderItemNotFoundException {
        purchaseOrderItemService.updateOrderItem(purchaseOrderItem);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(purchaseOrderItem, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable("id") Long id) throws PurchaseOrderItemNotFoundException {
        purchaseOrderItemService.deleteOrderItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreOrderItem(@PathVariable("id") Long id) throws PurchaseOrderItemNotFoundException {
        purchaseOrderItemService.restoreOrderItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
