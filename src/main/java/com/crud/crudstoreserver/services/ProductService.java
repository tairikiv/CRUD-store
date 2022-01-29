package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.ProductNotFoundException;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.ProductType;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product findById(Long id) throws ProductNotFoundException;

    List<Product> findByName(String name);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByProductType(ProductType productType);

    List<Product> findBySize(int size);

    void addProduct(Product product);

    void updateProduct(Product product) throws ProductNotFoundException;

    void deleteProductById(Long id) throws ProductNotFoundException;

    void restoreProductById(Long id) throws ProductNotFoundException;

}
