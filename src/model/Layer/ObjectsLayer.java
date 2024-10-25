package model.Layer;
import model.Game;
import model.Tile.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ObjectsLayer extends Layer{
    public ObjectsLayer(int size) {
        super(size);
        updateTiles(size);
        updateLayer(size);
    }

    @Override
    protected void updateTiles(int size) {
        ArrayList<Point> box_coordinates = new ArrayList<>();
        ArrayList<Point> treasure_coordinates = new ArrayList<>();



        for(Box box : Game.boxes){
            Point newPoint = new Point(box.getX(), box.getY());
            box_coordinates.add(newPoint);
        }
        for(Treasure treasure : Game.treasures){
            Point newPoint = new Point(treasure.getX(), treasure.getY());
            treasure_coordinates.add(newPoint);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (box_coordinates.contains(new Point(j, i))) {
                    for(int l = 0; l < box_coordinates.size(); l++) {
                        if(box_coordinates.get(l).equals(new Point(j, i))){
                            this.tiles.add(Game.boxes.get(l));
                        }
                    }
                }else if (treasure_coordinates.contains(new Point(j, i))) {
                    for(int l = 0; l < treasure_coordinates.size(); l++) {
                        if(treasure_coordinates.get(l).equals(new Point(j, i))){
                            this.tiles.add(Game.treasures.get(l));
                        }
                    }
                }
                else  if (i == 0 || j == 0 || i == size-1 || j == size-1) {
                    this.tiles.add(new Brick(j, i));
                }
                else if(j % 2 == 0 && i % 2 == 0){
                    this.tiles.add(new Block(j, i));
                }
                else {
                    this.tiles.add(new Empty(j, i));
                }
            }
        }
    }
}
