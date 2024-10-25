package model.Layer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.Tile.Tile;

import javax.swing.*;

public abstract class Layer {
    protected List<Tile> tiles = new ArrayList<>();
    protected JPanel layer;

    protected Layer(int size) {
        GridLayout grid = new GridLayout(size, size, 0, 0);
        layer = new JPanel(grid);
        layer.setOpaque(false);
        layer.setBounds(0, 0, size*30, size*30);
    }

    //update this layer(e.g, when player move it will be updated)
    public void  update(){
        this.tiles.clear();
        this.layer.removeAll();
        updateTiles(Game.map.getSize());
        updateLayer(Game.map.getSize());
    };

    //fill tiles array
    protected abstract void updateTiles(int size);

    //based of tiles, fill ImageIcons into layer(GridLayout)
    protected void updateLayer(int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel tileLabel = new JLabel();
                ImageIcon icon = new ImageIcon("assets/" + this.tiles.get(i*Game.map.getSize() + j).getVisual());
                tileLabel.setIcon(icon);
                tileLabel.setPreferredSize(new Dimension(30, 30));
                layer.add(tileLabel);
            }
        }
    }

    //Getter method to obtain JPanel for this layer
    public JPanel getLayer() {
        return this.layer;
    }

    public List<Tile> getTiles() {return this.tiles;}
}
