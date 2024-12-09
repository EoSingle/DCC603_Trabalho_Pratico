// MessageTest.java
package com.rpg.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testMessageCreation() {
        Message message = new Message("Sender", "Hello, World!");
        assertEquals("Sender", message.getSender());
        assertEquals("Hello, World!", message.getContent());
    }
}