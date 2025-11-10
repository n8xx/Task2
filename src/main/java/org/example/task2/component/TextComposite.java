package org.example.task2.component;

import java.util.ArrayList;

public class TextComposite extends TextComponent{
    ArrayList<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType textComponentType) {
        setComponentType(textComponentType);
    }

    public void addComponent(TextComponent textComponent) {
        components.add(textComponent);
    }
    public void removeComponent(TextComponent textComponent) {
        components.remove(textComponent);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(TextComponent component : components) {
            stringBuilder.append(component.toString());
        }
        return stringBuilder.toString();
    }
}