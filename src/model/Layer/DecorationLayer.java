package model.Layer;

import model.Game;
import model.Tile.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DecorationLayer extends Layer {
    public DecorationLayer(int size) {
        super(size);
        updateTiles(size);
        updateLayer(size);
    }

    @Override
    protected void updateTiles(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles.add(new Empty(j, i));
            }
        }
    }

    @Override
    protected void updateLayer(int size) {
        Random random = new Random();

        for (int i = 0; i < size*size; i++) {
            int randomNumber = random.nextInt(15);
            JLabel tileLabel = new JLabel();
            ImageIcon icon = new ImageIcon();
            if(randomNumber == 1) {
                icon = new ImageIcon("assets/grass1.png");
            }else if (randomNumber == 2) {
                icon = new ImageIcon("assets/grass2.png");
            }else if (randomNumber == 3) {
                icon = new ImageIcon("assets/grass3.png");
            }
            else{
                icon = new ImageIcon("assets/Empty.png");
            }
            tileLabel.setIcon(icon);
            tileLabel.setPreferredSize(new Dimension(30, 30));
            layer.add(tileLabel);
        }
    }
}
