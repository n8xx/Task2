package org.example.task2.parser;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComponentType;
import org.example.task2.component.TextLeaf;

public class SymbolParser extends AbstractTextParser {

    @Override
    public TextComponent parse(String text) {
        return new TextLeaf(text.charAt(0), TextComponentType.SYMBOL);
    }
}