package com.rpg;

import com.rpg.services.AuthService;
import com.rpg.services.TerminalService;
import com.rpg.services.GameService;

import java.util.Scanner;

import com.rpg.models.Campaign;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthService authService = new AuthService("src/main/resources/users/users.json");

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
                    GameService.createCampaign(username, scanner);
                    break;
                case 2: {
                    TerminalService.clearScreen();
                    if (GameService.listCampaigns(username) == 1) {
                        Campaign campaign = GameService.selectCampaign(username, scanner);
                        if (campaign != null) {
                            if (campaign.getMaster().equals(username)) {
                                GameService.playAsMaster(campaign, scanner);
                            } else {
                                GameService.playAsPlayer(campaign, username, scanner);
                            }
                        }
                        break;
                    }
                    break;
                }
                case 3:
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
