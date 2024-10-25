package model.Tile;

public class DetonatorBomb extends Bomb {

    public DetonatorBomb(int x, int y, Player owner) {
        super(x, y, owner);
        this.visual = "DetonatorBomb.png";
    }
}