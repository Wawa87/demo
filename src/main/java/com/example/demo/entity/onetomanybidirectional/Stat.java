package com.example.demo.entity.onetomanybidirectional;

import jakarta.persistence.*;

@Entity
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stat_id;
    private String name;
    @ManyToOne
    private Player player;

    public Long getId() {
        return stat_id;
    }

    public void setId(Long stat_id) {
        this.stat_id = stat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
