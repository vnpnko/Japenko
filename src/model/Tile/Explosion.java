package model.Tile;

public class Explosion extends Tile {
    Bomb owner_bomb;
    public Explosion(int x, int y, Bomb owner_bomb , String visual) {
        super(x, y);
        this.destructible = false;
        this.visual = visual;
        this.owner_bomb = owner_bomb;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Explosion)) {
            return false;
        }
        Explosion other = (Explosion) obj;
        return this.x == other.x && this.y == other.y;
    }

    public Bomb getOwner_bomb(){
        return owner_bomb;
    }
}
