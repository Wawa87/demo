package com.example.demo.entity.onetomany;

import jakarta.persistence.*;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long model_id;
    private String name;

    public Long getId() {
        return model_id;
    }

    public void setId(Long id) {
        this.model_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
