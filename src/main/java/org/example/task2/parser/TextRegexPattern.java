package org.example.task2.parser;

public final class TextRegexPattern {
    public static final String PARAGRAPH_PATTERN = "(\\R{2,})";
    public static final String SENTENCE_PATTERN = "(?<=[.!?])\\s+";
    public static final String LEXEME_PATTERN = "\\s+";
    public static final String WORD_OR_PUNCT_PATTERN = "\\p{Punct}";
    public static final String WORD_PATTERN= "\\p{L}+";


    private TextRegexPattern() {}

}