package org.example.task2.parser;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComposite;
import org.example.task2.component.TextComponentType;

public class LexemeParser extends AbstractTextParser {

    @Override
    public TextComponent parse(String text) {
        TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);

        String[] words = text.split(TextRegexPattern.WORD_PATTERN);

        for (String word : words) {
            lexemeComposite.addComponent(getNextParser().parse(word));
        }

        return lexemeComposite;
    }
}