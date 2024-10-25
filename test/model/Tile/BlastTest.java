package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlastTest {
    private Blast blast;

    @BeforeEach
    void setUp() {
        blast = new Blast(3, 4);
    }

    @Test
    void testDestructible() {
        assertFalse(blast.destructible, "Blast has to be indestructible");
    }
    @Test
    void testVisual() {
        assertEquals(blast.visual, "Blast.png", "Blast visual has to be Blast.png");
    }
}