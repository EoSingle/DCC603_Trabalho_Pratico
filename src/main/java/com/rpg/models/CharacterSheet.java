package com.rpg.models;

import java.util.Scanner;

public class CharacterSheet {
    private String characterName;
    private String characterClass;
    private String characterRace;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int level;

    public CharacterSheet(String characterName, String characterClass, String characterRace) {
        this.characterName = characterName;
        checkClass(characterClass);
        checkRace(characterRace);
        this.level = 1;
        this.strength = 8;
        this.dexterity = 8;
        this.constitution = 8;
        this.intelligence = 8;
        this.wisdom = 8;
        this.charisma = 8;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public int getLevel() {
        return level;
    }

    public int getAttribute(String attribute) {
        switch (attribute) {
            case "Força":
                return this.strength;
            case "Destreza":
                return this.dexterity;
            case "Constituição":
                return this.constitution;
            case "Inteligência":
                return this.intelligence;
            case "Sabedoria":
                return this.wisdom;
            case "Carisma":
                return this.charisma;
            default:
                return 0;
        }
    }

    public int setAttribute(String attribute, int value) {
        switch (attribute) {
            case "Força":
                return this.strength = value;
            case "Destreza":
                return this.dexterity = value;
            case "Constituição":
                return this.constitution = value;
            case "Inteligência":
                return this.intelligence = value;
            case "Sabedoria":
                return this.wisdom = value;
            case "Carisma":
                return this.charisma = value;
            default:
                return 0;
        }
    }

    public void setStats(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setCharacterClass(String characterClass) {
        checkClass(characterClass);
    }

    public void setCharacterRace(String characterRace) {
        checkRace(characterRace);
    }

    private void checkClass(String characterClass) {
        if (characterClass.equals("Guerreiro") || characterClass.equals("Mago") || characterClass.equals("Arqueiro")) {
            this.characterClass = characterClass;
        } else {
            System.out.println("Classe inválida. Tente novamente.");
        }
    }

    private void checkRace(String characterRace) {
        if (characterRace.equals("Humano") || characterRace.equals("Elfo") || characterRace.equals("Anão")) {
            this.characterRace = characterRace;
        } else {
            System.out.println("Raça inválida. Tente novamente.");
        }
    }

    public void showCaracterSheet() {
        System.out.println("Nome: " + this.characterName);
        System.out.println("Classe: " + this.characterClass);
        System.out.println("Raça: " + this.characterRace);
        System.out.println("Nível: " + this.level);
        System.out.println("=== Atributos ===");
        System.out.println("Força: " + this.strength);
        System.out.println("Destreza: " + this.dexterity);
        System.out.println("Constituição: " + this.constitution);
        System.out.println("Inteligência: " + this.intelligence);
        System.out.println("Sabedoria: " + this.wisdom);
        System.out.println("Carisma: " + this.charisma);
    }

    public void editName(Scanner scanner) {
        System.out.print("Digite o novo nome do personagem: ");
        String characterName = scanner.nextLine();
        this.characterName = characterName;
    }

    public void editClass(Scanner scanner) {
        System.out.print("Classes disponíveis: Guerreiro, Mago, Arqueiro\n");
        System.out.print("Digite a nova classe do personagem: ");
        String characterClass = scanner.nextLine();
        checkClass(characterClass);
    }

    public void editRace(Scanner scanner) {
        System.out.print("Raças disponíveis: Humano, Elfo, Anão\n");
        System.out.print("Digite a nova raça do personagem: ");
        String characterRace = scanner.nextLine();
        checkRace(characterRace);
    }

    public void editAttribute(Scanner scanner) {
        this.showCaracterSheet();
        System.out.print("Digite o atributo a editar: ");
        String attribute = scanner.nextLine();
        System.out.print("Digite o novo valor: ");
        int value = scanner.nextInt();
        if (value < 0 || value > 18) {
            System.out.println("Valor inválido.");
            return;
        }
        scanner.nextLine();
        this.setAttribute(attribute, value);
    }
}
