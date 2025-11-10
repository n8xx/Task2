package org.example.task2.reader;

import org.example.task2.exception.TextException;


public interface TextReader {
    String readFile(String path) throws TextException;
}