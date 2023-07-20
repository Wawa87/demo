package com.example.demo.repository;

import com.example.demo.entity.onetomanybidirectional.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatRepository extends JpaRepository<Stat, Long> {
    public List<Stat> findByPlayerNameIgnoreCase(String name);
}
