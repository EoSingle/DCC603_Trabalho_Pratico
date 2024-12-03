package com.rpg.models;

import java.util.Scanner;

import com.rpg.models.CharacterSheet;

public class Player {
    private String name;
    private CharacterSheet characterSheet;

    public Player(String name) {
        this.name = name;
        this.characterSheet = null;
    }

    public String getName() {
        return name;
    }

    public CharacterSheet getCharacterSheet() {
        return characterSheet;
    }

    public void createCharacterSheet(Scanner scanner) {
        System.out.print("Digite o nome do personagem: ");
        String characterName = scanner.nextLine();
        System.out.print("Digite a classe do personagem: ");
        String characterClass = scanner.nextLine();
        System.out.print("Digite o nível do personagem: ");
        int level = scanner.nextInt();
        scanner.nextLine();
        this.characterSheet = new CharacterSheet(characterName, characterClass, level);
    }

}
