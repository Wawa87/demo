package com.example.demo.repository;

import com.example.demo.entity.onetomany.Make;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeRepository extends JpaRepository<Make, Long> {
    public Make findByNameIgnoreCase(String make);
}
