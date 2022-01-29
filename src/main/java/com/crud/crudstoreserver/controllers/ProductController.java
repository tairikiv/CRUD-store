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
import java.util.Optional;

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
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(productService.findById(id));

        if (productOptional.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.FOUND);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) throws ProductNotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(productService.findByName(name));

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException(name);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.FOUND);
    }

    @GetMapping("/{price}")
    public ResponseEntity<Product> getProductByPrice(@PathVariable BigDecimal price) throws ProductNotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(productService.findByPrice(price));

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(price);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.FOUND);
    }

    @GetMapping("/{productType}")
    public ResponseEntity<Product> getProductByProductType(@PathVariable ProductType productType) throws ProductNotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(productService.findByProductType(productType));

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productType);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.FOUND);
    }

    @GetMapping("/{size}")
    public ResponseEntity<Product> getProductBySize(@PathVariable int size) throws ProductNotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(productService.findBySize(size));

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(size);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.FOUND);
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