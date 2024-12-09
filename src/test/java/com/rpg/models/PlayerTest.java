package com.rpg.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
    }

    @Test
    void testPlayerCreation() {
        assertEquals("TestPlayer", player.getName());
        assertNull(player.getCharacterSheet());
    }

    @Test
    void testCharacterSheetCreation() {
        String input = "CharacterName\nHumano\nGuerreiro\n" +
                       "4\n" + 
                       "4\n" + 
                       "4\n" + 
                       "4\n" + 
                       "3\n" + 
                       "3\n"; 
        Scanner scanner = new Scanner(input);
        player.createCharacterSheet(scanner);
        assertNotNull(player.getCharacterSheet());
        assertEquals("CharacterName", player.getCharacterSheet().getCharacterName());
    }
}