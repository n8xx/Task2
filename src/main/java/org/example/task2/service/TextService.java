package org.example.task2.service;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComposite;

import java.util.List;

public interface TextService {

    public int maxSentencesWithCommonWords(TextComposite text);
    public List<TextComponent> sortByLexemeCount(TextComposite text);
    public void swapEdgeLexemes(TextComposite text);

}