package model.Tile;

import model.Game;

public class Tile {
    protected int x;
    protected int y;
    protected boolean isBoundary;
    protected boolean destructible;
    protected String visual;
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
//        this.isBoundary = false;
        if (Game.map != null && (this.x == 0 || this.y == 0 || this.x == Game.map.getSize()-1 || this.y == Game.map.getSize()-1)) {
            this.isBoundary = true;
        } else {
            this.isBoundary = false;
        }
    }

    // Getter method to obtain the x-coordinate
    public int getX() {
        return x;
    }

    // Getter method to obtain the y-coordinate
    public int getY() {
        return y;
    }

    // Setter method to set the x-coordinate
    public void setX(int x) {
        this.x = x;
    }

    // Setter method to set the y-coordinate
    public void setY(int y) {
        this.y = y;
    }
    public String getVisual() {
        return visual;
    }

}
