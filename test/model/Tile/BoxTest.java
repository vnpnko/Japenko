package model.Tile;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {
    @BeforeEach
    public void setUp() {
        Game.treasures = new ArrayList<>();
        Game.boxes = new ArrayList<>();
    }

    @Test
    public void testBoxCreation(){
        Box box = new Box(1,1);
        assertEquals(1, box.getX());
        assertEquals(1, box.getY());
        assertTrue(box.destructible);
        assertEquals("Box.png", box.getVisual());
        assertNotNull(box.treasure_type);
    }

    @Test
    public void testBoxDestruction(){
        Box box = new Box(1, 1);
        box.destroy();
        assertEquals(0, Game.boxes.size());
        assertEquals(1, Game.treasures.size());
    }
}
