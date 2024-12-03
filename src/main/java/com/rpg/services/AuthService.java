package com.rpg.services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AuthService {
    private final String filePath;
    private Map<String, String> users = new HashMap<>();

    public AuthService(String filePath) {
        this.filePath = filePath;
        loadUsers();
    }

    // Carregar usuários do arquivo JSON
    private void loadUsers() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // Inicializar com uma lista vazia
                }
            }

            JSONParser parser = new JSONParser();
            JSONArray userList = (JSONArray) parser.parse(new FileReader(file));
            for (Object obj : userList) {
                JSONObject userObj = (JSONObject) obj;
                users.put((String) userObj.get("username"), (String) userObj.get("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Salvar usuários no arquivo JSON
    @SuppressWarnings("unchecked")
    private void saveUsers() {
        JSONArray userList = new JSONArray();
        for (Map.Entry<String, String> entry : users.entrySet()) {
            JSONObject userObj = new JSONObject();
            userObj.put("username", entry.getKey());
            userObj.put("password", entry.getValue());
            userList.add(userObj);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(userList.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Registrar novo usuário
    public void register(Scanner scanner) {
        System.out.print("Escolha um nome de usuário: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Nome de usuário já existe. Tente novamente.");
            return;
        }

        System.out.print("Escolha uma senha: ");
        String password = scanner.nextLine();
        String hashedPassword = hashPassword(password);

        users.put(username, hashedPassword);
        saveUsers();
        System.out.println("Usuário registrado com sucesso!");
    }

    // Login de usuário existente
    public String login(Scanner scanner) {
        System.out.print("Nome de usuário: ");
        String username = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();
        String hashedPassword = hashPassword(password);

        if (users.containsKey(username) && users.get(username).equals(hashedPassword)) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + username + "!");
            return username;
        } else {
            System.out.println("Nome de usuário ou senha incorretos.");
            return null;
        }
    }

    // Gerar hash da senha usando SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash da senha.", e);
        }
    }
}
