package com.rpg.integration;

import com.rpg.models.*;
import com.rpg.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ChatSystemIntegrationTest extends IntegrationTestBase {
    
    private ChatService chatService;
    private CampaignService campaignService;
    private static final String TEST_CAMPAIGN = "TestCampaign";

    @BeforeEach
    void setUp() {
        super.setUp();
        File chatFile = new File("src/main/resources/chat/" + TEST_CAMPAIGN + "Chat.json");
        if (chatFile.exists()) {
            chatFile.delete();
        }
        
        chatService = new ChatService();
        campaignService = new CampaignService();
    }
    
    @Test
    void testChatSystemFlow() {
        // 1. Criar campanha
        Campaign campaign = new Campaign(TEST_CAMPAIGN, "TestMaster");
        campaignService.saveCampaign(campaign);
        
        // 2. Inicializar chat
        chatService.createFile(TEST_CAMPAIGN);
        chatService.loadChat(TEST_CAMPAIGN);
        
        // 3. Simular conversa
        chatService.addMessage("TestMaster", "Bem vindos à campanha!");
        chatService.addMessage("Player1", "Olá, mestre!");
        chatService.addMessage("TestMaster", "Vamos começar a aventura!");
        
        // 4. Verificar persistência
        ChatService newChatService = new ChatService();
        newChatService.loadChat(TEST_CAMPAIGN);
        List<Message> messages = newChatService.getMessages();
        
        // Verificações
        assertEquals(3, messages.size(), "Deve haver exatamente 3 mensagens no chat");
        assertEquals("TestMaster", messages.get(0).getSender());
        assertEquals("Bem vindos à campanha!", messages.get(0).getContent());
        assertEquals("Player1", messages.get(1).getSender());
        assertEquals("Olá, mestre!", messages.get(1).getContent());
        assertEquals("TestMaster", messages.get(2).getSender());
        assertEquals("Vamos começar a aventura!", messages.get(2).getContent());
    }
} 