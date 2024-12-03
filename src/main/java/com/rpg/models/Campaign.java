package com.rpg.models;

import java.util.ArrayList;
import java.util.List;

public class Campaign {
    private String name;
    private List<Player> players;

    public Campaign(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
