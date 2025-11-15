package org.example.task2.parser;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComponentType;
import org.example.task2.component.TextComposite;


public class SentenceParser extends AbstractTextParser {

    @Override
    public TextComponent parse(String text) {
        TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);

        String[] sentences = text.split(TextRegexPattern.LEXEME_PATTERN);

        for(String sentence : sentences) {
            sentenceComposite.addComponent(getNextParser().parse(sentence.trim()));
        }
        return sentenceComposite;
    }
}