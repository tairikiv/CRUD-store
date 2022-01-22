package com.crud.crudstoreserver.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;

    private String firstName;

    @OneToOne
    private Role Role;

    @OneToOne
    private Address address;

    @OneToOne
    private Payment payment;

    private String phoneNumber;

}
