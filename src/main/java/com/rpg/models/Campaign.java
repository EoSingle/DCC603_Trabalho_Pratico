package com.rpg.models;

import java.util.ArrayList;
import java.util.List;

import com.rpg.services.ChatService;

public class Campaign {
    private String name;
    private String master;
    private List<Player> players;
    private List<Message> messages;

    public Campaign(String name, String master) {
        this.name = name;
        this.master = master;
        this.players = new ArrayList<>();
        ChatService chatService = new ChatService();
        chatService.createFile(name);
        this.messages = new ArrayList<>();
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
