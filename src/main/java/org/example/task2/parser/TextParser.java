package org.example.task2.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComposite;
import org.example.task2.component.TextComponentType;


public class TextParser extends AbstractTextParser {
    public static final Logger logger = LogManager.getLogger();
    @Override
    public TextComponent parse(String text) {
        logger.info("Starting text parsing");

        TextComposite textComposite = new TextComposite(TextComponentType.TEXT);
        String[] paragraphs = text.strip().split(TextRegexPattern.PARAGRAPH_PATTERN);

        logger.info("Found " + paragraphs.length + " potential paragraphs");

        int processedParagraphs = 0;
        for (String paragraph : paragraphs) {
            String stripedParagraph = paragraph.strip();
            if (!stripedParagraph.isEmpty()) {
                if (getNextParser() != null) {
                    textComposite.addComponent(getNextParser().parse(stripedParagraph));
                    processedParagraphs++;
                } else {
                    logger.warn("No next parser configured in chain!");
                }
            }
        }

        logger.info("Successfully processed " + processedParagraphs + " paragraphs");
        return textComposite;
    }
}



