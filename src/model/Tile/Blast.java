package model.Tile;

public class Blast extends Tile {
    public Blast(int x, int y) {
        super(x, y);
        this.destructible = false;
        this.visual = "Blast.png";
    }
}
