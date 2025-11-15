package org.example.task2.service.impl;

import org.example.task2.component.TextComponent;
import org.example.task2.component.TextComponentType;
import org.example.task2.component.TextComposite;
import org.example.task2.service.TextService;

import java.util.*;

public class TextServiceImpl implements TextService {

    @Override
    public int maxSentencesWithCommonWords(TextComposite text) {
        List<TextComponent> sentences = getSentences(text);
        Map<String, Set<Integer>> wordToSentenceMap = new HashMap<>();

        for (int i = 0; i < sentences.size(); i++) {
            Set<String> words = extractWords(sentences.get(i));
            for (String word : words) {
                wordToSentenceMap.computeIfAbsent(word, k -> new HashSet<>()).add(i);
            }
        }

        return wordToSentenceMap.values().stream()
                .mapToInt(Set::size)
                .max()
                .orElse(0);
    }

    @Override
    public List<TextComponent> sortByLexemeCount(TextComposite text) {
        List<TextComponent> sentences = getSentences(text);
        sentences.sort(Comparator.comparingInt(this::countLexemes));
        return sentences;
    }

    @Override
    public void swapEdgeLexemes(TextComposite text) {
        List<TextComponent> sentences = getSentences(text);

        for (TextComponent sentence : sentences) {
            if (sentence instanceof TextComposite composite) {
                List<TextComponent> lexemes = new ArrayList<>(composite.getComponents());
                if (lexemes.size() > 1) {
                    Collections.swap(lexemes, 0, lexemes.size() - 1);
                    composite.setComponents(lexemes);
                }
            }
        }
    }

    private List<TextComponent> getSentences(TextComposite text) {
        List<TextComponent> sentences = new ArrayList<>();
        for (TextComponent paragraph : text.getComponents()) {
            if (paragraph instanceof TextComposite paragraphComposite) {
                sentences.addAll(paragraphComposite.getComponents());
            }
        }
        return sentences;
    }


    private Set<String> extractWords(TextComponent sentence) {
        Set<String> words = new HashSet<>();

        if (sentence instanceof TextComposite sentenceComposite) {
            for (TextComponent lexeme : sentenceComposite.getComponents()) {
                if (lexeme instanceof TextComposite lexemeComposite) {
                    for (TextComponent component : lexemeComposite.getComponents()) {
                        if (component.getComponentType() == TextComponentType.WORD) {
                            words.add(component.toString().toLowerCase());
                        }
                    }
                }
            }
        }

        return words;
    }
    private int countLexemes(TextComponent sentence) {
        if (sentence instanceof TextComposite sentenceComposite) {
            return sentenceComposite.getComponents().size();
        }
        return 0;
    }
}