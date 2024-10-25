package model.Tile;

import model.TreasureType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TreasureTest {
    private Treasure treasure;

    @BeforeEach
    public void setUp(){
        treasure = new Treasure(1, 1, "Treasure.png", TreasureType.BOMB_POWER_UP);
    }

    @Test
    public void testTreasureCreation(){
        assertEquals(1, treasure.getX());
        assertEquals(1, treasure.getY());
        assertEquals("Treasure.png", treasure.visual);
        assertEquals(TreasureType.BOMB_POWER_UP, treasure.getType());
    }

    @Test
    public void testGetType(){
        assertEquals(TreasureType.BOMB_POWER_UP, treasure.getType());
    }
}
