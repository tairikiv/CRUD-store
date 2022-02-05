package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.ProductNotFoundException;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.ProductType;
import com.crud.crudstoreserver.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> showAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        try {
            Product product = productService.findById(id);
            return new ResponseEntity<>(product, HttpStatus.FOUND);
        } catch (ProductNotFoundException productNotFoundException){
            return new ResponseEntity<>(productNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("/{price}")
    public List<Product> getProductByPrice(@PathVariable BigDecimal price){
        return productService.findByPrice(price);
    }

    @GetMapping("/{productType}")
    public List<Product> getProductByProductType(@PathVariable ProductType productType){
        return productService.findByProductType(productType);
    }

    @GetMapping("/{size}")
    public List<Product> getProductBySize(@PathVariable int size){
        return productService.findBySize(size);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product){
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product) throws ProductNotFoundException {
        productService.updateProduct(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(product, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.restoreProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
