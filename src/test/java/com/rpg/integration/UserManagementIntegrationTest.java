package com.rpg.integration;

import com.rpg.services.*;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserManagementIntegrationTest extends IntegrationTestBase {
    
    private static final String TEST_USERS_FILE = "src/test/resources/users.json";

    @Test
    void testUserManagementFlow() {
        AuthService authService = new AuthService(TEST_USERS_FILE);
        
        // 1. Registro de múltiplos usuários
        Scanner scanner1 = new Scanner("user1\npass1\n");
        Scanner scanner2 = new Scanner("user2\npass2\n");
        
        authService.register(scanner1);
        authService.register(scanner2);
        
        // 2. Tentativa de login com credenciais corretas
        scanner1 = new Scanner("user1\npass1\n");
        String username1 = authService.login(scanner1);
        assertEquals("user1", username1);
        
        // 3. Tentativa de login com credenciais incorretas
        scanner2 = new Scanner("user2\nwrongpass\n");
        String username2 = authService.login(scanner2);
        assertNull(username2);
        
        // 4. Tentativa de registro com nome de usuário existente
        Scanner scanner3 = new Scanner("user1\npass3\n");
        authService.register(scanner3);
        scanner3 = new Scanner("user1\npass3\n");
        String username3 = authService.login(scanner3);
        assertNull(username3);
    }
} 