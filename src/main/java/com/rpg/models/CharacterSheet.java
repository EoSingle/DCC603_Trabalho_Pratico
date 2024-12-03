package com.rpg.models;

public class CharacterSheet {
    private String characterName;
    private String characterClass;
    private int level;

    public CharacterSheet(String characterName, String characterClass, int level) {
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.level = level;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public int getLevel() {
        return level;
    }
}
