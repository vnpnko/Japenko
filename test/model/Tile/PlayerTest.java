package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Player1.png", 1);
    }

    @Test
    void testRefreshForNewRound() {
        player.refreshForNewRound();
        assertTrue(player.isAlive(), "Player should be alive after refresh.");
        assertEquals(1, player.getPower_of_bombs(), "Power of bombs should be reset to 1.");
    }

    @Test
    void testIsAlive() {
        assertTrue(player.isAlive(), "Player should initially be alive.");
    }

    @Test
    void testSetAlive() {
        player.setAlive(false);
        assertFalse(player.isAlive(), "Player should be set to not alive.");
    }

    @Test
    void testGetSpeed() {
        int initialSpeed = player.getSpeed();
        assertEquals(1, initialSpeed, "Initial speed should be 1.");
    }

    @Test
    void testIncreaseSpeed() {
        player.increaseSpeed();
        assertEquals(2, player.getSpeed(), "Speed should increase by 1.");
    }

    @Test
    void testDecreaseSpeed() {
        player.increaseSpeed(); // Increase first to avoid negative speed.
        player.decreaseSpeed();
        assertEquals(1, player.getSpeed(), "Speed should decrease to initial value.");
    }
}
