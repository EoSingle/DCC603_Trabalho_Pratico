// FileUtilsTest.java
package com.rpg.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void testGetFileExtension() {
        assertEquals(".txt", FIleUtils.getFileExtension("document.txt"));
        assertEquals("", FIleUtils.getFileExtension("file"));
        assertEquals(".java", FIleUtils.getFileExtension("Main.java"));
    }
}