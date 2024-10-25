package model.Tile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BonusTest {
    @Test
    public void testBonusCreation(){
        Bonus bonus = new Bonus(1,1);
        assertEquals(1, bonus.getX());
        assertEquals(1, bonus.getY());
        assertFalse(bonus.destructible);
        assertEquals("Bonus.png", bonus.visual);
    }
}
