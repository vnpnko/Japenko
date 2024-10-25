package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {
    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field(3, 4);
    }

    @Test
    void testDestructible() {
        assertFalse(field.destructible, "Field has to be indestructible");
    }
    @Test
    void testVisual() {
        assertEquals(field.visual, "Field.png", "Field visual has to be Field.png");
    }
}
