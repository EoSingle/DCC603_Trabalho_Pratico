package com.rpg.services;

public class TerminalService {
    public static void clearScreen() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        catch (Exception e) {
            System.out.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }

    public static void clearScreen(int waitTime) {
        try {
            Thread.sleep(waitTime);
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        catch (Exception e) {
            System.out.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }
}
