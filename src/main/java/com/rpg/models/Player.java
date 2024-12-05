package com.rpg.models;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import com.rpg.services.TerminalService;

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
        System.out.print("Raças disponíveis: Humano, Elfo, Anão\n");
        System.out.print("Digite a raça do personagem: ");
        String characterRace = scanner.nextLine();
        System.out.print("Classes disponíveis: Guerreiro, Mago, Arqueiro\n");
        System.out.print("Digite a classe do personagem: ");
        String characterClass = scanner.nextLine();
        this.characterSheet = new CharacterSheet(characterName, characterClass, characterRace);
        // Escolha dos atributos, o personagem começa com 8 em todos e pode distribuir 22 pontos
        ArrayList<String> attributes = new ArrayList<String>( 
            List.of("Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma")
        );
        int points = 22;
        System.out.println("=== Distribuição de pontos de atributos ===");
        System.out.println("Você tem 22 pontos para distribuir entre os atributos, começando com 8 em cada. O máximo é 18.");
        for (String attribute : attributes) {
            System.out.println(attribute + ": " + this.characterSheet.getAttribute(attribute));
            System.out.println("Pontos restantes: " + points);
            System.out.print("Digite os pontos a adicionar para " + attribute + ": ");
            int value = scanner.nextInt();
            scanner.nextLine();
            if (value > points) {
                System.out.println("Você não tem pontos suficientes.");
                break;
            }
            if (value > 10) {
                System.out.println("Você não pode adicionar mais de 10 pontos de uma vez.");
                break;
            }
            points -= value;
            if (points < 0) {
                System.out.println("Você não tem mais pontos.");
                break;
            }

            this.characterSheet.setAttribute(attribute, value + this.characterSheet.getAttribute(attribute));
        }
    }

    public void editCharacterSheet(Scanner scanner) {
        if (this.characterSheet == null) {
            System.out.println("Personagem não criado.");
            return;
        }
        TerminalService.clearScreen();
        this.characterSheet.showCaracterSheet();
        System.out.println("=== Opções de edição ===");
        System.out.println("1. Editar nome");
        System.out.println("2. Editar raça");
        System.out.println("3. Editar classe");
        System.out.println("4. Editar atributo");
        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.characterSheet.editName(scanner);
                break;
            case 2:
                this.characterSheet.editRace(scanner);
                break;
            case 3:
                this.characterSheet.editClass(scanner);
                break;
            case 4:
                TerminalService.clearScreen();
                this.characterSheet.editAttribute(scanner);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void deleteCharacterSheet() {
        this.characterSheet = null;
    }
}
