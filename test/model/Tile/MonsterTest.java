package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    private Monster monster;

    @BeforeEach
    void setUp() {
        monster = new Monster("basicMonster.png");
    }

    @Test
    void testIsAlive() {
        assertTrue(monster.isAlive(), "Monster should initially be alive.");
    }

    @Test
    void testSetAndGetDirection() {
        monster.setDirection(Monster.Direction.LEFT);
        assertEquals(Monster.Direction.LEFT, monster.getDirection(), "Monster direction should be set to LEFT.");
    }

    @Test
    void testSetAndGetSpeed() {
        monster.setSpeed(2);
        assertEquals(2, monster.getSpeed(), "Monster speed should be set to 2.");
    }
}