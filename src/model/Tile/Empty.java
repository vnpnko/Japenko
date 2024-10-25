package model.Tile;

public class Empty extends Tile {
    public Empty(int x, int y) {
        super(x, y);
        this.destructible = false;
        this.visual = "Empty.png";
    }
}
