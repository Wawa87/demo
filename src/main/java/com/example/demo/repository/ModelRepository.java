package com.example.demo.repository;

import com.example.demo.entity.onetomany.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
    public Model findByNameIgnoreCase(String name);
}
