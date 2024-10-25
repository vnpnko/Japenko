package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostMonsterTest {
    private GhostMonster ghostMonster;

    @BeforeEach
    void setUp() {
        ghostMonster = new GhostMonster("ghostMonster.png");
    }

    @Test
    void testIsAlive() {
        assertTrue(ghostMonster.isAlive(), "GhostMonster should initially be alive.");
    }

    @Test
    void testSetAndGetDirection() {
        ghostMonster.setDirection(GhostMonster.Direction.LEFT);
        assertEquals(GhostMonster.Direction.LEFT, ghostMonster.getDirection(), "GhostMonster direction should be set to LEFT.");
    }

    @Test
    void testSetAndGetSpeed() {
        ghostMonster.setSpeed(2);
        assertEquals(2, ghostMonster.getSpeed(), "GhostMonster speed should be set to 2.");
    }
}
