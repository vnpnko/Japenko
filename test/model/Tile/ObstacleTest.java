package model.Tile;

import model.Tile.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {
    @Test
    public void testObstacleCreation(){
        Player owner = new Player("Box.png", 1);
        Obstacle obstacle = new Obstacle(1,1,owner);
        assertEquals(1, obstacle.getX());
        assertEquals(1, obstacle.getY());
        assertTrue(obstacle.destructible);
        assertEquals("Box.png", obstacle.visual);
        assertNull(obstacle.treasure_type);
        assertEquals(owner, obstacle.getOwner());
    }
}
