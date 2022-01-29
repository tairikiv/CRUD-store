package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Optional<Product> findByPrice(BigDecimal price);

    Optional<Product> findByProductType(ProductType productType);

    Optional<Product> findBySize(int size);
}
