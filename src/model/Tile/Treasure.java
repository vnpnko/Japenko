package model.Tile;

import model.TreasureType;

public class Treasure extends Tile {
    private final TreasureType type;
    public Treasure(int x, int y, String visual, TreasureType type) {
        super(x, y);
        this.visual = visual;
        this.type = type;
    }

    public TreasureType getType() {
        return this.type;
    }
}
