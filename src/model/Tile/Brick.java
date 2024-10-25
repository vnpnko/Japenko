package model.Tile;

public class Brick extends Tile {
    public Brick(int x, int y) {
        super(x, y);
        this.destructible = false;
        this.visual = "Brick.png";
    }
}
