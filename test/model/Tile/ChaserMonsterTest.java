package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChaserMonsterTest {
    private ChaserMonster chaserMonster;
    private Player player;

    @BeforeEach
    void setUp() {
        chaserMonster = new ChaserMonster("chaserMonster.png");
        chaserMonster.setX(0);
        chaserMonster.setY(0);
    }

    @Test
    void testIsAlive() {
        assertTrue(chaserMonster.isAlive(), "ChaserMonster should initially be alive.");
    }

    @Test
    void testSetAndGetDirection() {
        chaserMonster.setDirection(Monster.Direction.LEFT);
        assertEquals(Monster.Direction.LEFT, chaserMonster.getDirection(), "ChaserMonster direction should be set to LEFT.");
    }

    @Test
    void testSetAndGetSpeed() {
        chaserMonster.setSpeed(4);
        assertEquals(4, chaserMonster.getSpeed(), "ChaserMonster speed should be set to 4.");
    }
}
