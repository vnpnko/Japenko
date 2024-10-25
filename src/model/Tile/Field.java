package model.Tile;

public class Field extends Tile {
    public Field(int x, int y) {
        super(x, y);
        this.destructible = false;
        this.visual = "Field.png";
    }
}
