package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetonatorBombTest {
    private DetonatorBomb detonatorBomb;

    @BeforeEach
    void setUp() {
        detonatorBomb = new DetonatorBomb(3, 4, new Player("Player1.png", 1));
    }

    @Test
    void testVisual() {
        assertEquals(detonatorBomb.visual, "DetonatorBomb.png", "DetonatorBomb visual has to be DetonatorBomb.png");
    }
}