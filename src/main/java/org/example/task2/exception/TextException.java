package org.example.task2.exception;

public class TextException extends Exception {

    public TextException() {
        super();
    }

    public TextException(String message) {
        super(message);
    }

    public TextException(Throwable reason) {
        super(reason);
    }

    public TextException(String message, Throwable reason) {
        super(message, reason);
    }
}
