package org.example.task2.component;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextLeafTest {

    @Test
    public void testToString() {
        TextLeaf leaf = new TextLeaf('A', TextComponentType.SYMBOL);
        assertEquals("A", leaf.toString());
    }

    @Test
    public void testGetType() {
        TextLeaf leaf = new TextLeaf('B', TextComponentType.SYMBOL);
        assertEquals(TextComponentType.SYMBOL, leaf.getComponentType());
    }
}