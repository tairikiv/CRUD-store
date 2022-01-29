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
    public Product findByName(String name) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByName(name);

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException(name);
        }
        return productOptional.get();
    }

    @Override
    public Product findByPrice(BigDecimal price) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByPrice(price);

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException(price);
        }
        return productOptional.get();
    }

    @Override
    public Product findByProductType(ProductType productType) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByProductType(productType);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productType);
        }
        return productOptional.get();
    }

    @Override
    public Product findBySize(int size) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findBySize(size);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(size);
        }
        return productOptional.get();
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
