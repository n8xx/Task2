package org.example.task2.component;

public abstract class TextComponent {

    private TextComponentType componentType;

    public TextComponentType getComponentType(){
        return this.componentType;
    }

    public void setComponentType(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public abstract String toString();
}
