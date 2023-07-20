package com.example.demo.repository;

import com.example.demo.entity.onetomanybidirectional.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Player findByNameIgnoreCase(String name);
}
