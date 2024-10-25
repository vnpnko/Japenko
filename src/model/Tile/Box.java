package model.Tile;

import model.Game;
import model.TreasureType;
import java.util.Random;

public class Box extends Tile {
    protected TreasureType treasure_type;

    public Box(int x, int y) {
        super(x, y);
        this.destructible = true;
        this.visual = "Box.png";
        // Randomly determine the contents of this box
        Random random = new Random();
        this.treasure_type = TreasureType.values()[random.nextInt(TreasureType.values().length)];
    }
    
    // Method that gets called when the box is destroyed
    public void destroy() {
        String visual = switch (treasure_type) {
            case FORBIDDEN -> "item_forbidden_bomb.png";
            case BOMB_POWER_DOWN -> "item_power_down.png";
            case BOMB_POWER_UP -> "item_bomb_power_up.png";
            case BOMB_INCREASE -> "item_bomb_increase.png";
            case INVINCIBILITY -> "item_invincibility.png";
            case GHOST -> "item_ghost.png";
            case DETONATOR -> "item_detonator.png";
            case ROLLERSKATE -> "item_roller_skate.png";
            case OBSTACLE -> "item_obstacle.png";
            case SPEED_DOWN -> "item_speed_down.png";
        };
        Treasure newTreasure = new Treasure(x, y, visual, treasure_type);
        Game.treasures.add(newTreasure);
        Game.boxes.remove(this);
    }

}
