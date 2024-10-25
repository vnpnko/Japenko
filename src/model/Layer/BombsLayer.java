//package model.Layer;
//
//public class BombLayer extends Layer{
//
//}
package model.Layer;

import model.Game;
import model.Tile.Bomb;
import model.Tile.NormalBomb;
import model.Tile.Empty;
import model.Tile.Explosion;

import java.awt.*;
import java.util.ArrayList;

public class BombsLayer extends Layer{
    public BombsLayer(int size) {
        super(size);
        updateTiles(size);
        updateLayer(size);
    }

    @Override
    protected void updateTiles(int size) {
        ArrayList<Point> bombs_coordinates = new ArrayList<>();
        ArrayList<Point> explosions_coordinates = new ArrayList<>();

        for(Bomb bomb : Game.bombs){
            Point newPoint = new Point(bomb.getX(), bomb.getY());
            bombs_coordinates.add(newPoint);
        }
        for(Explosion explosion : Game.explosions){
            Point newPoint = new Point(explosion.getX(), explosion.getY());
            explosions_coordinates.add(newPoint);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //add bombs
                if (bombs_coordinates.contains(new Point(j, i))) {
                    for(int l = 0; l < bombs_coordinates.size(); l++) {
                        if(bombs_coordinates.get(l).equals(new Point(j, i))){
                            this.tiles.add(Game.bombs.get(l));
                        }
                    }
                }
                //add explosions
                else if(explosions_coordinates.contains(new Point(j, i))) {
                    for(int l = 0; l < explosions_coordinates.size(); l++) {
                        if(explosions_coordinates.get(l).equals(new Point(j, i))){
                            if(this.tiles.contains(Game.explosions.get(l))){
                                this.tiles.remove(Game.explosions.get(l));
                            }
                            this.tiles.add(Game.explosions.get(l));
                        }
                    }
                }
                else {
                    this.tiles.add(new Empty(j, i));
                }
            }
        }
    }
}
