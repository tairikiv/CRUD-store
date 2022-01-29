package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.ProductNotFoundException;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.ProductType;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product findById(Long id) throws ProductNotFoundException;

    Product findByName(String name) throws ProductNotFoundException;

    Product findByPrice(BigDecimal price) throws ProductNotFoundException;

    Product findByProductType(ProductType productType) throws ProductNotFoundException;

    Product findBySize(int size) throws ProductNotFoundException;

    void addProduct(Product product);

    void updateProduct(Product product) throws ProductNotFoundException;

    void deleteProductById(Long id) throws ProductNotFoundException;

    void restoreProductById(Long id) throws ProductNotFoundException;

}
