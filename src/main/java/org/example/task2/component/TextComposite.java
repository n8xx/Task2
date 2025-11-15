package org.example.task2.component;


import java.util.ArrayList;
import java.util.List;


public class TextComposite extends TextComponent {
    private static final String TABULATION = "\t";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private ArrayList<TextComponent> components = new ArrayList<>();


    public TextComposite(TextComponentType textComponentType) {
        setComponentType(textComponentType);
    }

    public void addComponent(TextComponent textComponent) {
        components.add(textComponent);
    }
    public void removeComponent(TextComponent textComponent) {
        components.remove(textComponent);
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    public void setComponents(List<TextComponent> newComponents) {
        this.components = new ArrayList<>(newComponents);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent component : components) {

            boolean needsPrefix = component.getComponentType() == TextComponentType.PARAGRAPH;
            String delimiter = getDelimiter(component.getComponentType());

            if (needsPrefix) {
                sb.append(TABULATION);
            }

            sb.append(component);

            if (delimiter != null) {
                sb.append(delimiter);
            }
        }
        return sb.toString().stripTrailing();
    }
    private String getDelimiter(TextComponentType type) {
        return switch (type) {
            case PARAGRAPH -> NEW_LINE;
            case SENTENCE -> SPACE;
            case LEXEME -> null;
            case WORD -> null;
            default -> SPACE;
        };
    }
}