// CharacterSheetTest.java
package com.rpg.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterSheetTest {

    private CharacterSheet characterSheet;

    @BeforeEach
    void setUp() {
        characterSheet = new CharacterSheet("CharacterName", "Guerreiro", "Humano");
    }

    @Test
    void testCharacterSheetCreation() {
        assertEquals("CharacterName", characterSheet.getCharacterName());
        assertEquals("Guerreiro", characterSheet.getCharacterClass());
        assertEquals("Humano", characterSheet.getCharacterRace());
    }

    @Test
    void testSetAttribute() {
        characterSheet.setAttribute("Força", 10);
        assertEquals(10, characterSheet.getAttribute("Força"));
    }
}