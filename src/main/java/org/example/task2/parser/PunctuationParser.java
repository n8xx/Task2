package org.example.task2.parser;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComponentType;
import org.example.task2.component.TextComposite;

public class PunctuationParser extends AbstractTextParser {

    @Override
    public TextComponent parse(String text) {
        TextComposite punctuationComposite = new TextComposite(TextComponentType.PUNCTUATION);

        for (char c : text.toCharArray()) {
            punctuationComposite.addComponent(getNextParser().parse(String.valueOf(c)));
        }

        return punctuationComposite;
    }
}