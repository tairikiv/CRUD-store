package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Min(1)
    private int size;

    private boolean isActive;
}
