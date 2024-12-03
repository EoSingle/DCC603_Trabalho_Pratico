package com.rpg.models;

public class Player {
    private String name;
    private CharacterSheet characterSheet;
    private String type;

    public Player(String name, CharacterSheet characterSheet, String type) {
        this.name = name;
        this.characterSheet = characterSheet;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public CharacterSheet getCharacterSheet() {
        return characterSheet;
    }

    public boolean isMaster(){
        return type.equals("master");
    }
}
