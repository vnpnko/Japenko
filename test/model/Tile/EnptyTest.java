package model.Tile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EnptyTest {
    @Test
    public void testEmptyCreation(){
        Empty empty = new Empty(1,1);
        assertEquals(1, empty.getX());
        assertEquals(1, empty.getY());
        assertFalse(empty.destructible);
        assertEquals("Empty.png", empty.visual);
    }
}
