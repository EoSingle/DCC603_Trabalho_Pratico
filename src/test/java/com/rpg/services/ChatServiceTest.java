package com.rpg.services;

import com.rpg.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatServiceTest {

    private ChatService chatService;
    private static final String TEST_CAMPAIGN = "TestCampaign";

    @BeforeEach
    void setUp() {
        // Clean up any existing test chat file
        File chatFile = new File("src/main/resources/chat/" + TEST_CAMPAIGN + "Chat.json");
        if (chatFile.exists()) {
            chatFile.delete();
        }
        
        chatService = new ChatService();
        chatService.createFile(TEST_CAMPAIGN);
        chatService.loadChat(TEST_CAMPAIGN);
    }

    @Test
    void testAddMessage() {
        chatService.addMessage("User1", "Hello!");
        List<Message> messages = chatService.getMessages();
        assertEquals(1, messages.size());
        assertEquals("User1", messages.get(0).getSender());
        assertEquals("Hello!", messages.get(0).getContent());
    }
}