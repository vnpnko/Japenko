package model.Tile;

import model.Game;

public class Obstacle extends Box{
    private final Player owner;
    public Obstacle(int x, int y, Player owner) {
        super(x, y);
        this.owner = owner;
        this.visual = "Box.png";
        this.treasure_type = null;
    }

    @Override
    public void destroy(){
        Game.boxes.remove(this);
    }

    public Player getOwner() {
        return owner;
    }
}
