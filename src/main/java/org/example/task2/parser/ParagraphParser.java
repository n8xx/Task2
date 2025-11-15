package org.example.task2.parser;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComposite;
import org.example.task2.component.TextComponentType;


public class ParagraphParser extends AbstractTextParser{

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);

        String[] sentences = text.split(TextRegexPattern.SENTENCE_PATTERN);

        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                paragraphComposite.addComponent(getNextParser().parse(sentence));
            }
        }

        return paragraphComposite;
    }
}