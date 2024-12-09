package com.rpg.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService("src/test/resources/users.json");
    }

    @Test
    void testRegisterAndLogin() {
        Scanner scanner = new Scanner("newuser\npassword\n");
        authService.register(scanner);
        scanner = new Scanner("newuser\npassword\n");
        String username = authService.login(scanner);
        assertEquals("newuser", username);
    }
}