package com.rpg;

import com.rpg.services.CampaignService;
//import com.rpg.services.DiceRoller;
import com.rpg.services.AuthService;
import com.rpg.services.TerminalService;

import java.util.List;
import java.util.Scanner;

import com.rpg.models.Campaign;
//import com.rpg.models.Player;
//import com.rpg.models.CharacterSheet;
import com.rpg.models.Player;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthService authService = new AuthService("src/main/resources/users/users.json");
    private static final CampaignService campaignService = new CampaignService();

    public static void main(String[] args) {
        String username = startAuthentication();
        mainMenu(scanner, username);
    }

    public static String startAuthentication(){
        while (true) {
            System.out.println("\n=== Sistema de RPG Online ===");
            System.out.println("1. Login");
            System.out.println("2. Registrar");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    TerminalService.clearScreen();
                    String username = authService.login(scanner);
                    if (username != null) {
                        return username;
                    }
                    break;
                case 2:
                    TerminalService.clearScreen();
                    authService.register(scanner);
                    break;
                case 3:
                    TerminalService.clearScreen();
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    TerminalService.clearScreen(1000);
            }
        }
    }

    public static void mainMenu(Scanner scanner, String username) {
        while(true) {
            TerminalService.clearScreen();
            System.out.println("\n=== Menu Principal ===");
            System.out.println("Bem-vindo, " + username + "!");
            System.out.println("1. Criar nova campanha");
            System.out.println("2. Ver Campanhas");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createCampaign(username);
                    break;
                case 2: {
                    TerminalService.clearScreen();
                    List<Campaign> campaignsMaster = campaignService.listCampaignsMaster(username);
                    List<Campaign> campaignsPlayer = campaignService.listCampaignsPlayer(username);
                    if (campaignsMaster.size() == 0 && campaignsPlayer.size() == 0) {
                        System.out.println("Nenhuma campanha encontrada.");
                        TerminalService.clearScreen(1000);
                        break;
                    } else {
                        System.out.println("Campanhas disponíveis:");
                        for (Campaign c : campaignsMaster) {
                            System.out.println(" - " + c.getName() + " (Mestre)");
                        }
                        for (Campaign c : campaignsPlayer) {
                            System.out.println(" - " + c.getName() + " (Jogador)");
                        }
                        while(true) {
                            System.out.println("Digite o nome de uma campanha ou 'sair': ");
                            String campaignName = scanner.nextLine();
                            if (campaignName.equals("sair")) {
                                break;
                            }
                            Campaign campaign = campaignService.loadCampaign(campaignName);
                            if (campaign == null) {
                                System.out.println("Nome de campanha inválido. Tente novamente.");
                            } else {
                                if (campaign.getMaster().equals(username)) {
                                    playAsMaster(campaign);
                                } else {
                                    playAsPlayer(campaign, username);
                                }
                                break;
                            }                            
                        }
                        break;
                    }
                }
                case 3:
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void createCampaign(String master) {
        System.out.print("Digite o nome da campanha: ");
        String name = scanner.nextLine();
        Campaign campaign = new Campaign(name, master);
        campaignService.saveCampaign(campaign);
        System.out.println("Campanha '" + name + "' criada com sucesso!");
    }

    private static void playAsMaster(Campaign campaign){
        while(true) {
            TerminalService.clearScreen();
            System.out.println("=== Menu do Mestre ===");
            System.out.println("1. Ver Jogadores");
            System.out.println("2. Ver Personagens");
            System.out.println("3. Gerenciar Mapa");
            System.out.println("4. Logs");
            System.out.println("5. Chat");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            // TODO: Implementar todas as opções do mestre
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
                        case 1:
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
                        case 2:
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
                    }
                case 2:
                    System.out.println("=== Personagens ===");
                    break;
                case 3:
                    System.out.println("=== Mapa ===");
                    break;
                case 4:
                    System.out.println("=== Logs ===");
                    break;
                case 5:
                    System.out.println("=== Chat ===");
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void playAsPlayer(Campaign campaign, String username){
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
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            // TODO: Implementar todas as opções do jogador
            switch (option) {
                case 1:
                    TerminalService.clearScreen();
                    System.out.println("=== Personagem ===");
                    if (player.getCharacterSheet() != null) {
                        System.out.println("Nome: " + player.getCharacterSheet().getCharacterName());
                        System.out.println("Classe: " + player.getCharacterSheet().getCharacterClass());
                        System.out.println("Nível: " + player.getCharacterSheet().getLevel());
                        System.out.println("=== Opções ===");
                        System.out.println("1. Editar personagem");
                        System.out.println("2. Voltar");

                        System.out.print("Escolha uma opção: ");
                        int characterOption = scanner.nextInt();
                        scanner.nextLine();

                        // TODO: Implementar a edição do personagem
                        switch (characterOption) {
                            case 1:
                                player.createCharacterSheet(scanner);
                                campaignService.saveCampaign(campaign);
                                System.out.println("Personagem editado com sucesso!");
                                TerminalService.clearScreen(1000);
                                break;
                            case 2:
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
                            TerminalService.clearScreen(1000);
                        }
                    }
                    break;
                case 2:
                    System.out.println("=== Mapa ===");
                    break;
                case 3:
                    System.out.println("=== Chat ===");
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
