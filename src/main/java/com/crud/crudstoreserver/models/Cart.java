package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private  OrderItem orderItem;
}
