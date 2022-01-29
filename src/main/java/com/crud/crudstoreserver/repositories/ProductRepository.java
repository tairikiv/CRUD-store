package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);

    List<Product> findAllByPrice(BigDecimal price);

    List<Product> findAllByProductType(ProductType productType);

    List<Product> findAllBySize(int size);
}
