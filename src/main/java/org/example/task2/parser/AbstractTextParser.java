package org.example.task2.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.task2.component.TextComponent;

public abstract class AbstractTextParser {
    private AbstractTextParser nextParser;

    public void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public AbstractTextParser getNextParser() {
        return this.nextParser;
    }

    public abstract TextComponent parse(String text);
}
