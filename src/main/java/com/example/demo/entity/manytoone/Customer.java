package com.example.demo.entity.manytoone;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cust_id;
    private String name;

    public Long getId() {
        return cust_id;
    }

    public void setId(Long id) {
        this.cust_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
