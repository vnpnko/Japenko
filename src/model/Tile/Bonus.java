package model.Tile;

public class Bonus extends Tile {
    public Bonus(int x, int y) {
        super(x, y);
        this.destructible = false;
        this.visual = "Bonus.png";
    }
}
