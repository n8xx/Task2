package org.example.task2.parser;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComponentType;
import org.example.task2.component.TextComposite;

public class WordParser extends AbstractTextParser {

    @Override
    public TextComponent parse(String text) {

        if (!text.matches(TextRegexPattern.WORD_PATTERN)) {
            return getNextParser().parse(text);
        }

        TextComposite wordComposite = new TextComposite(TextComponentType.WORD);

        for (char c : text.toCharArray()) {
            wordComposite.addComponent(getNextParser().parse(String.valueOf(c)));
        }

        return wordComposite;
    }
}