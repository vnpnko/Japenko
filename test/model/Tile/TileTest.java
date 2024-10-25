package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    private Tile tile;

    @BeforeEach
    void setUp() {
        tile = new Tile(3, 4);
    }

    @Test
    void testGetX() {
        assertEquals(tile.getX(), 3, "GetX should return 3 as it is assigned in the constructor");
    }
    @Test
    void testSetX() {
        tile.setX(4);
        assertEquals(tile.getX(), 4, "GetX should return 4 after setting new value with SetX");
    }
    @Test
    void testGetY() {
        assertEquals(tile.getY(), 4, "GetY should return 4 as it is assigned in the constructor");
    }
    @Test
    void testSetY() {
        tile.setY(3);
        assertEquals(tile.getY(), 3, "GetY should return 3 after setting new value with SetY");
    }
}