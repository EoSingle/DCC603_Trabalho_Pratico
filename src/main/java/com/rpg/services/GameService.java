package com.rpg.services;

import java.util.Scanner;
import java.util.List;

import com.rpg.models.Campaign;
import com.rpg.models.Player;

public class GameService {
    private static final CampaignService campaignService = new CampaignService();

    public static void createCampaign(String master, Scanner scanner) {
        System.out.print("Digite o nome da campanha: ");
        String campaignName = scanner.nextLine();
        Campaign campaign = new Campaign(campaignName, master);
        campaignService.saveCampaign(campaign);
        System.out.println("Campanha '" + campaignName + "' criada com sucesso!");
    }

    public static int listCampaigns(String username) {
        List<Campaign> campaignsMaster = campaignService.listCampaignsMaster(username);
        List<Campaign> campaignsPlayer = campaignService.listCampaignsPlayer(username);
        if (campaignsMaster.size() == 0 && campaignsPlayer.size() == 0) {
            System.out.println("Nenhuma campanha encontrada.");
            TerminalService.clearScreen(1000);
            return 0;
        } else {
            System.out.println("Campanhas disponíveis:");
            for (Campaign c : campaignsMaster) {
                System.out.println(" - " + c.getName() + " (Mestre)");
            }
            for (Campaign c : campaignsPlayer) {
                System.out.println(" - " + c.getName() + " (Jogador)");
            }
            return 1;
        }
    }

    public static Campaign selectCampaign(String campaingName, Scanner scanner){
        while (true) {
            System.out.println("Digite o nome de uma campanha ou 'sair': ");
            String campaignName = scanner.nextLine();
            if (campaignName.equals("sair")) {
                break;
            }
            Campaign campaign = campaignService.loadCampaign(campaignName);
            if (campaign == null) {
                System.out.println("Nome de campanha inválido. Tente novamente.");
            } else {
                return campaign;
            }
        }
        return null;
    }

    public static void playAsPlayer(Campaign campaign, String username, Scanner scanner) {
        Player player = null;
        for (Player p : campaign.getPlayers()) {
            if (p.getName().equals(username)) {
                player = p;
                break;
            }
        }
        if (player == null) {
            System.out.println("Erro: Jogador não encontrado na campanha.");
            return;
        }

        while(true) {
            TerminalService.clearScreen();
            System.out.println("=== Menu do Jogador ===");
            System.out.println("1. Ver Personagem");
            System.out.println("2. Ver Mapa");
            System.out.println("3. Chat");
            System.out.println("4. Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    TerminalService.clearScreen();
                    System.out.println("=== Personagem ===");
                    if (player.getCharacterSheet() != null) {
                        player.getCharacterSheet().showCaracterSheet();
                        System.out.println("=== Opções ===");
                        System.out.println("1. Editar personagem");
                        System.out.println("2. Deletar personagem");
                        System.out.print("3. Voltar\n");

                        System.out.print("Escolha uma opção: ");
                        int characterOption = scanner.nextInt();
                        scanner.nextLine();

                        switch (characterOption) {
                            case 1:
                                player.editCharacterSheet(scanner);
                                break;
                            case 2:
                                player.deleteCharacterSheet();
                                campaignService.saveCampaign(campaign);
                                System.out.println("Personagem deletado com sucesso!");
                                TerminalService.clearScreen(1000);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                TerminalService.clearScreen(1000);
                        }

                    } else {
                        System.out.println("Personagem não criado. Deseja criar um personagem? (s/n)");
                        String createCharacter = scanner.nextLine();
                        if (createCharacter.equals("s")) {
                            player.createCharacterSheet(scanner);
                            campaignService.saveCampaign(campaign);
                            System.out.println("Personagem criado com sucesso!");
                            TerminalService.clearScreen(2000);
                        }
                    }
                    break;
                case 2:
                    System.out.println("=== Mapa ===");
                    System.out.println("Em desenvolvimento...");
                    break;
                case 3:
                    System.out.println("=== Chat ===");
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void playAsMaster(Campaign campaign, Scanner scanner){
        while(true) {
            TerminalService.clearScreen();
            System.out.println("=== Menu do Mestre ===");
            System.out.println("1. Ver Jogadores");
            System.out.println("2. Ver Personagens");
            System.out.println("3. Mapa");
            System.out.println("4. Logs");
            System.out.println("5. Chat");
            System.out.println("6. Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    TerminalService.clearScreen();
                    System.out.println("=== Jogadores ===");
                    if (campaign.getPlayers().size() != 0) {
                        for (int i = 0; i < campaign.getPlayers().size(); i++) {
                            System.out.println((i + 1) + ". " + campaign.getPlayers().get(i).getName());
                        }
                    } else {
                        System.out.println("Nenhum jogador na campanha.");
                    }
                    System.out.println("=== Opções ===");
                    System.out.println("1. Adicionar jogador");
                    System.out.println("2. Remover jogador");
                    System.out.println("3. Voltar");

                    System.out.print("Escolha uma opção: ");
                    int playerOption = scanner.nextInt();
                    scanner.nextLine();

                    switch (playerOption) {
                        case 1: // Pode virar função addPlayer()
                            TerminalService.clearScreen();
                            System.out.println("=== Adicionar Jogador ===");
                            System.out.print("Digite o nome do jogador ou 'cancelar': ");
                            String playerName = scanner.nextLine();
                            if (playerName.equals("cancelar")) {
                                break;
                            }
                            Player player = new Player(playerName);
                            campaign.addPlayer(player);
                            campaignService.saveCampaign(campaign);
                            System.out.println("Jogador adicionado com sucesso!");
                            TerminalService.clearScreen(1000);
                            break;
                        case 2: // Pode virar função removePlayer()
                            System.out.print("Digite o nome do jogador a ser removido: ");
                            String playerNameToRemove = scanner.nextLine();
                            Player playerToRemove = null;
                            for (Player p : campaign.getPlayers()) {
                                if (p.getName().equals(playerNameToRemove)) {
                                    playerToRemove = p;
                                    break;
                                }
                            }
                            if (playerToRemove != null) {
                                campaign.getPlayers().remove(playerToRemove);
                                campaignService.saveCampaign(campaign);
                                System.out.println("Jogador removido com sucesso!");
                                TerminalService.clearScreen(1000);
                            } else {
                                System.out.println("Jogador não encontrado.");
                                TerminalService.clearScreen(1000);
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            TerminalService.clearScreen(1000);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("=== Personagens ===");
                    if (campaign.getPlayers().size() != 0) {
                        for (int i = 0; i < campaign.getPlayers().size(); i++) {
                            if(campaign.getPlayers().get(i).getCharacterSheet() == null) {
                                System.out.println("Personagem não criado para " + campaign.getPlayers().get(i).getName());
                                System.out.println("--------------------");
                                continue;
                            }
                            System.out.println("Jogador: " + campaign.getPlayers().get(i).getName());
                            System.out.println("Nome do Personagem: " + campaign.getPlayers().get(i).getCharacterSheet().getCharacterName());
                            System.out.println("Raça: " + campaign.getPlayers().get(i).getCharacterSheet().getCharacterRace());
                            System.out.println("Classe: " + campaign.getPlayers().get(i).getCharacterSheet().getCharacterClass());
                            System.out.println("Nível: " + campaign.getPlayers().get(i).getCharacterSheet().getLevel());
                            System.out.println("--------------------");
                        }
                    } else {
                        System.out.println("Nenhum jogador na campanha.");
                        break;
                    }
                    System.out.println("=== Opções ===");
                    System.out.println("1. Editar personagem");
                    System.out.println("2. Deletar personagem");
                    System.out.println("3. Voltar");

                    System.out.print("Escolha uma opção: ");
                    int characterOption = scanner.nextInt();
                    scanner.nextLine();

                    switch (characterOption) {
                        case 1:
                            System.out.print("Digite o nome do jogador para editar o personagem: ");
                            String playerName = scanner.nextLine();
                            Player player = null;
                            for (Player p : campaign.getPlayers()) {
                                if (p.getName().equals(playerName)) {
                                    player = p;
                                    break;
                                }
                            }
                            if (player != null) {
                                player.editCharacterSheet(scanner);
                                campaignService.saveCampaign(campaign);
                                System.out.println("Personagem editado com sucesso!");
                                TerminalService.clearScreen(2000);
                            } else {
                                System.out.println("Jogador não encontrado.");
                                TerminalService.clearScreen(1000);
                            }
                            break;
                        case 2:
                            System.out.print("Digite o nome do jogador para deletar o personagem: ");
                            String playerToDelete = scanner.nextLine();
                            Player playerAux = null;
                            for (Player p : campaign.getPlayers()) {
                                if (p.getName().equals(playerToDelete)) {
                                    playerAux = p;
                                    break;
                                }
                            }
                            if (playerAux != null) {
                                playerAux.deleteCharacterSheet();
                                campaignService.saveCampaign(campaign);
                                System.out.println("Personagem deletado com sucesso!");
                                TerminalService.clearScreen(1000);
                            } else {
                                System.out.println("Jogador não encontrado.");
                                TerminalService.clearScreen(1000);
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            TerminalService.clearScreen(1000);
                    }
                    break;
                case 3:
                    System.out.println("=== Mapa ===");
                    System.out.println("Em desenvolvimento...");
                    break;
                case 4:
                    System.out.println("=== Logs ===");
                    System.out.println("Em desenvolvimento...");
                    break;
                case 5:
                    System.out.println("=== Chat ===");
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
