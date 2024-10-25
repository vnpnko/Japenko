package model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExplosionTest {

    private Explosion explosion;

    @BeforeEach
    void setUp() {
        explosion = new Explosion(3, 4, new Bomb(3, 3, new Player("Player1.png", 1)), "exp_col.png");
    }

    @Test
    void testDestructible() {
        assertFalse(explosion.destructible, "Explosion has to be indestructible");
    }
    @Test
    void testEquals() {
        assertEquals(explosion, new Explosion(3, 4, new Bomb(3, 3, new Player("Player1.png", 1)), "exp_col.png"), "Explosion and object of same class with same parameters should be equal");
        assertNotEquals(explosion, new Player("Player1.png", 1), "Explosion and object of different class should not be equal");
        assertEquals(explosion, new Explosion(3, 4, new Bomb(3, 4, new Player("Player1.png", 1)), "exp_col.png"), "Two explosions with same x and y but different other parameters should be equal");
        assertNotEquals(explosion, new Explosion(4, 4, new Bomb(3, 3, new Player("Player1.png", 1)), "exp_col.png"), "Two explosions with different x and y should not be not equal");
    }
    @Test
    void testBombOwner() {
        assertEquals(explosion.owner_bomb, explosion.getOwner_bomb(), "getOwner_bomb should return the owner_bomb of the explosion");
    }
}