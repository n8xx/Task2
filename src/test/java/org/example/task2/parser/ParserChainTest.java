package org.example.task2.parser;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComponentType;
import org.example.task2.component.TextComposite;
import org.example.task2.parser.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserChainTest {

    private AbstractTextParser buildParserChain() {
        SymbolParser symbol = new SymbolParser();
        PunctuationParser punct = new PunctuationParser();
        punct.setNextParser(symbol);

        WordParser word = new WordParser();
        word.setNextParser(symbol);

        LexemeParser lexeme = new LexemeParser();
        lexeme.setNextParser(word);

        SentenceParser sentence = new SentenceParser();
        sentence.setNextParser(lexeme);

        ParagraphParser paragraph = new ParagraphParser();
        paragraph.setNextParser(sentence);

        TextParser text = new TextParser();
        text.setNextParser(paragraph);

        return text;
    }

    @Test
    void testSymbolParser() {
        SymbolParser parser = new SymbolParser();
        TextComponent result = parser.parse("A");

        assertAll(
                () -> assertEquals(TextComponentType.SYMBOL, result.getComponentType()),
                () -> assertEquals("A", result.toString())
        );
    }

    @Test
    void testWordParser() {
        WordParser parser = new WordParser();
        SymbolParser symbol = new SymbolParser();
        parser.setNextParser(symbol);

        TextComponent result = parser.parse("Hello");
        List<TextComponent> chars = ((TextComposite) result).getComponents();

        assertAll(
                () -> assertEquals(5, chars.size()),
                () -> assertEquals("H", chars.get(0).toString()),
                () -> assertEquals(TextComponentType.WORD, result.getComponentType())
        );
    }

    @Test
    void testWordParserNonLetter() {
        WordParser parser = new WordParser();
        SymbolParser symbol = new SymbolParser();
        parser.setNextParser(symbol);

        TextComponent result = parser.parse("?");

        assertAll(
                () -> assertEquals(TextComponentType.SYMBOL, result.getComponentType())
        );
    }

    @Test
    void testPunctuationParser() {
        PunctuationParser parser = new PunctuationParser();
        SymbolParser symbol = new SymbolParser();
        parser.setNextParser(symbol);

        TextComposite composite = (TextComposite) parser.parse("!?");

        assertAll(
                () -> assertEquals(2, composite.getComponents().size()),
                () -> assertEquals("!", composite.getComponents().get(0).toString()),
                () -> assertEquals("?", composite.getComponents().get(1).toString())
        );
    }

    @Test
    void testLexemeParser() {
        LexemeParser parser = new LexemeParser();
        WordParser word = new WordParser();
        SymbolParser symbol = new SymbolParser();
        word.setNextParser(symbol);
        parser.setNextParser(word);

        TextComposite composite = (TextComposite) parser.parse(" Hello! ");

        assertAll(
                () -> assertEquals(2, composite.getComponents().size()),
                () -> assertEquals(TextComponentType.WORD, composite.getComponents().get(0).getComponentType()),
                () -> assertEquals(TextComponentType.SYMBOL, composite.getComponents().get(1).getComponentType())
        );
    }

    @Test
    void testSentenceParser() {
        SentenceParser parser = new SentenceParser();
        LexemeParser lexeme = new LexemeParser();
        WordParser word = new WordParser();
        SymbolParser symbol = new SymbolParser();

        word.setNextParser(symbol);
        lexeme.setNextParser(word);
        parser.setNextParser(lexeme);

        TextComposite result = (TextComposite) parser.parse("Hello world test");

        assertAll(
                () -> assertEquals(3, result.getComponents().size())
        );
    }

    @Test
    void testParagraphParser() {
        ParagraphParser parser = new ParagraphParser();
        SentenceParser sentence = new SentenceParser();
        LexemeParser lexeme = new LexemeParser();
        WordParser word = new WordParser();
        SymbolParser symbol = new SymbolParser();

        word.setNextParser(symbol);
        lexeme.setNextParser(word);
        sentence.setNextParser(lexeme);
        parser.setNextParser(sentence);

        TextComposite result = (TextComposite) parser.parse("Hello. Bye.");

        assertAll(
                () -> assertEquals(2, result.getComponents().size())
        );
    }

    @Test
    void testTextParser() {
        TextParser parser = (TextParser) buildParserChain();
        TextComposite result = (TextComposite) parser.parse("A. B.");

        assertAll(
                () -> assertEquals(1, result.getComponents().size())
        );
    }

    @Test
    void testFullChain() {
        AbstractTextParser parser = buildParserChain();

        TextComposite root = (TextComposite) parser.parse("Hello world.\n\nTest text!");

        List<TextComponent> paragraphs = root.getComponents();
        TextComposite p1 = (TextComposite) paragraphs.get(0);
        TextComposite p2 = (TextComposite) paragraphs.get(1);

        assertAll(
                () -> assertEquals(2, paragraphs.size()),
                () -> assertEquals(1, p1.getComponents().size()),
                () -> assertEquals(1, p2.getComponents().size())
        );
    }
}