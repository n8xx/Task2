package org.example.task2.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.task2.exception.TextException;
import org.example.task2.reader.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public String readFile(String path) throws TextException {
        try {
            URL resource = getClass().getClassLoader().getResource(path);

            if (resource == null) {
                throw new TextException("File not found: " + path);
            }

            Path filePath = Path.of(resource.toURI());
            String result = Files.readString(filePath);
            logger.info("file readed");
            return result;

        } catch (IOException e){
            logger.warn("IO problem while reading file "+path, e);
            throw new TextException("IO problem while reading file "+path, e);
        } catch (URISyntaxException e) {
            logger.warn("Uri problem for file "+path, e);
            throw new TextException("Uri problem for file "+path, e);
        }
    }
}