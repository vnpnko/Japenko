package model.Tile;

public class Block extends Tile {
    public Block(int x, int y) {
        super(x, y);
        this.destructible = false;
        this.visual = "Block.png";
    }
}
