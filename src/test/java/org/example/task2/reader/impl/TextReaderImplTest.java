package org.example.task2.reader.impl;

import org.example.task2.exception.TextException;
import org.example.task2.reader.TextReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderImplTest {

    @Test
    void readFile_success() throws TextException {
        TextReader reader = new TextReaderImpl();
        String text = reader.readFile("data/text.txt");
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", text.trim());
    }

    @Test
    void readFile_notFound() {
        TextReader reader = new TextReaderImpl();
        assertThrows(TextException.class, () -> reader.readFile("missing.txt"));
    }
}