package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.ProductNotFoundException;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.ProductType;
import com.crud.crudstoreserver.repositories.ProductRepository;
import com.crud.crudstoreserver.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> findByPrice(BigDecimal price) {
        return productRepository.findAllByPrice(price);
    }

    @Override
    public List<Product> findByProductType(ProductType productType) {
        return productRepository.findAllByProductType(productType);
    }

    @Override
    public List<Product> findBySize(int size){
        return productRepository.findAllBySize(size);
    }

    @Override
    public void addProduct(Product product) {
        product.setActive(true);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) throws ProductNotFoundException {
        if(!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException(product.getId());
        }
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(findById(id));

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        } else {
            Product product = productOptional.get();
            product.setActive(false);
            productRepository.saveAndFlush(product);
        }
    }

    @Override
    public void restoreProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(findById(id));

        if (productOptional.isEmpty()) {
            throw  new ProductNotFoundException(id);
        } else {
            Product product = productOptional.get();
            product.setActive(true);
            productRepository.saveAndFlush(product);
        }
    }
}
