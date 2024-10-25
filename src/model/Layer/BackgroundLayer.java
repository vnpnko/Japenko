package model.Layer;

import model.Tile.Field;

public class BackgroundLayer extends Layer{
    public BackgroundLayer(int size) {
        super(size);
        updateTiles(size);
        updateLayer(size);
    }

    @Override
    protected void updateTiles(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles.add(new Field(i, j));
            }
        }
    }
}
