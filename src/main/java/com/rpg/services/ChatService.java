package com.rpg.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rpg.models.Message;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class ChatService {
    private static final String CHAT_FOLDER = "src/main/resources/chat/";
    private List<Message> messages;
    private final Gson gson;
    private String FILE_PATH;

    public ChatService() {
        this.createFolder();
        this.messages = null;
        this.gson = new Gson();
    }

    public void loadChat(String campaignName) {
        this.FILE_PATH = CHAT_FOLDER + campaignName + "Chat.json";
        this.messages = loadMessages();
    }

    // Cria a pasta chat/ caso ela não exista
    private void createFolder() {
        try {
            File folder = new File(CHAT_FOLDER);
            if (!folder.exists()) {
                boolean created = folder.mkdirs();
                if (created) {
                    System.out.println("Pasta 'chat/' criada com sucesso.");
                } else {
                    System.err.println("Erro ao criar a pasta 'chat/'.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao criar a pasta 'chat/': " + e.getMessage());
        }
    }

    public void createFile(String campaignName) {
        try {
            File file = new File(CHAT_FOLDER + campaignName + "Chat.json");
            if (!file.exists()) {
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // Inicializar com uma lista vazia
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(String sender, String content) {
        Message message = new Message(sender, content);
        messages.add(message);
        saveMessages();
    }

    public List<Message> getMessages() {
        return messages;
    }

    private void saveMessages() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(messages, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Message> loadMessages() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void showChat() {
        for (Message message : messages) {
            System.out.println(message.getSender() + ": " + message.getContent());
        }
    }
}
