package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal price;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private int size;
}
