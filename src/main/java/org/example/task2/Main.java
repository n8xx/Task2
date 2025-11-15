package org.example.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.task2.exception.TextException;
import org.example.task2.reader.impl.TextReaderImpl;

public class Main {
    public static void main(String[] args) throws TextException {
        final Logger logger = LogManager.getLogger();
        TextReaderImpl textReader = new TextReaderImpl();
        String result = textReader.readFile("text.txt");
        logger.info(result);

    }
}
