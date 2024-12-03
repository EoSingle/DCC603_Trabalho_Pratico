package com.rpg;

import com.rpg.services.CampaignService;
import com.rpg.services.DiceRoller;

import java.util.Scanner;

import com.rpg.models.Campaign;
import com.rpg.models.Player;
import com.rpg.models.CharacterSheet;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CampaignService campaignService = new CampaignService();

    public static void main(String[] args) {
        Campaign campaign = null;

        while (true) {
            System.out.println("\n=== Sistema de RPG Online ===");
            System.out.println("1. Criar nova campanha");
            System.out.println("2. Adicionar jogador à campanha");
            System.out.println("3. Rolar dados");
            System.out.println("4. Exibir detalhes da campanha");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    campaign = createCampaign();
                    break;
                case 2:
                    if (campaign != null) {
                        addPlayerToCampaign(campaign);
                    } else {
                        System.out.println("Nenhuma campanha criada ainda!");
                    }
                    break;
                case 3:
                    rollDice();
                    break;
                case 4:
                    if (campaign != null) {
                        displayCampaignDetails(campaign);
                    } else {
                        System.out.println("Nenhuma campanha criada ainda!");
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static Campaign createCampaign() {
        System.out.print("Digite o nome da campanha: ");
        String name = scanner.nextLine();
        Campaign campaign = new Campaign(name);
        campaignService.saveCampaign(campaign); // Salva a campanha inicialmente
        System.out.println("Campanha '" + name + "' criada com sucesso!");
        return campaign;
    }

    private static void addPlayerToCampaign(Campaign campaign) {
        System.out.print("Digite o nome do jogador: ");
        String playerName = scanner.nextLine();

        System.out.print("Digite o nome do personagem: ");
        String characterName = scanner.nextLine();

        System.out.print("Digite a classe do personagem: ");
        String characterClass = scanner.nextLine();

        System.out.print("Digite o nível do personagem: ");
        int level = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        CharacterSheet characterSheet = new CharacterSheet(characterName, characterClass, level);
        Player player = new Player(playerName, characterSheet, "player");
        campaign.addPlayer(player);

        campaignService.saveCampaign(campaign); // Atualiza o arquivo da campanha
        System.out.println("Jogador '" + playerName + "' adicionado com sucesso!");
    }

    private static void rollDice() {
        System.out.print("Digite o número de lados do dado (ex: 20 para D20): ");
        int sides = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        int result = DiceRoller.rollDice(sides);
        System.out.println("Você rolou um D" + sides + " e obteve: " + result);
    }

    private static void displayCampaignDetails(Campaign campaign) {
        System.out.println("\n=== Detalhes da Campanha ===");
        System.out.println("Nome da campanha: " + campaign.getName());
        System.out.println("Jogadores:");
        for (Player player : campaign.getPlayers()) {
            System.out.println(" - Jogador: " + player.getName());
            System.out.println("   Personagem: " + player.getCharacterSheet().getCharacterName());
            System.out.println("   Classe: " + player.getCharacterSheet().getCharacterClass());
            System.out.println("   Nível: " + player.getCharacterSheet().getLevel());
        }
    }
}