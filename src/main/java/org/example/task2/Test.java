package org.example.task2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
    private static final Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[]args) {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.error("Error message");

        System.out.println("Проверьте папку 'logs' в корне проекта");
    }
}