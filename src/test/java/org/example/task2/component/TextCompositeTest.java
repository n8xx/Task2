package org.example.task2.component;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TextCompositeTest {

    @Test
    public void testAddComponent() {
        TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
        TextLeaf leaf = new TextLeaf('A', TextComponentType.SYMBOL);

        composite.addComponent(leaf);

        assertEquals(1, composite.getComponents().size());
        assertEquals(leaf, composite.getComponents().get(0));
    }

    @Test
    public void testRemoveComponent() {
        TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
        TextLeaf leaf = new TextLeaf('A', TextComponentType.SYMBOL);

        composite.addComponent(leaf);
        composite.removeComponent(leaf);

        assertEquals(0, composite.getComponents().size());
    }

    @Test
    public void testSetComponents() {
        TextComposite composite = new TextComposite(TextComponentType.SENTENCE);

        TextLeaf a = new TextLeaf('A', TextComponentType.SYMBOL);
        TextLeaf b = new TextLeaf('B', TextComponentType.SYMBOL);

        composite.setComponents(List.of(a, b));

        assertEquals(2, composite.getComponents().size());
        assertEquals(a, composite.getComponents().get(0));
        assertEquals(b, composite.getComponents().get(1));
    }

    @Test
    public void testToStringSentence() {
        TextComposite composite = new TextComposite(TextComponentType.SENTENCE);

        composite.addComponent(new TextLeaf('H', TextComponentType.SYMBOL));
        composite.addComponent(new TextLeaf('i', TextComponentType.SYMBOL));

        assertEquals("H i", composite.toString());
    }

    @Test
    public void testToStringParagraph() {
        TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);

        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        sentence.addComponent(new TextLeaf('X', TextComponentType.SYMBOL));

        composite.addComponent(sentence);

        assertEquals("\tX", composite.toString());
    }

}