package com.rpg.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.File;

public class IntegrationTestBase {
    
    @BeforeEach
    void setUp() {
        cleanTestFiles();
    }

    @AfterEach
    void tearDown() {
        cleanTestFiles();
    }

    private void cleanTestFiles() {
        // Limpa arquivos de teste
        deleteDirectory(new File("src/test/resources/campaigns"));
        deleteDirectory(new File("src/test/resources/chat"));
        deleteDirectory(new File("src/test/resources/users.json"));
    }

    private void deleteDirectory(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteDirectory(f);
                }
            }
        }
        file.delete();
    }
} 