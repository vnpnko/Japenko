package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BrickTest {
    private Brick brick;

    @BeforeEach
    void setUp() {
        brick = new Brick(3, 4);
    }

    @Test
    void testDestructible() {
        assertFalse(brick.destructible, "Brick has to be indestructible");
    }
    @Test
    void testVisual() {
        assertEquals(brick.visual, "Brick.png", "Brick visual has to be Brick.png");
    }
}