package model.Tile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    @Test
    public void testBlockCreation(){
        Block block = new Block(1,1);
        assertEquals(1, block.getX());
        assertEquals(1, block.getY());
        assertFalse(block.destructible);
        assertEquals("Block.png", block.visual);
    }
}
