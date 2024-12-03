package com.rpg.models;

import java.util.ArrayList;
import java.util.List;

public class Campaign {
    private String name;
    private String master;
    private List<Player> players;

    public Campaign(String name, String master) {
        this.name = name;
        this.master = master;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getMaster() {
        return master;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
