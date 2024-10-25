package model;

import model.Layer.*;

import java.util.HashMap;
;

public class Map {
    private final String type;
    private final int size;
    private java.util.Map<String, Layer> layers;

    public Map(String mapType) {
        type = mapType;
        switch (mapType) {
            case "SmallMap":
                size = 13;
                break;
            case "MediumMap":
                size = 15;
                break;
            case "LargeMap":
                size = 19;
                break;
            default:
                throw new IllegalArgumentException("Invalid map name: " + type);
        }
    }

    public void updateMap(){
        //region >> init layers hashmap
        layers = new HashMap<String, Layer>();
        //endregion

        //region >> generate new layers
        Layer BackgroundLayer  = new BackgroundLayer(size); //background layer
        Layer DecorationLayer  = new DecorationLayer(size); //decoration
        Layer BombsLayer = new BombsLayer(size);
        Layer ObjectsLayer = new ObjectsLayer(size);
        Layer CharactersLayer = new CharactersLayer(size);//objects layer
        //endregion

        //region >> Add each layer in layers hashmap
        layers.put("Background", BackgroundLayer);
        layers.put("Decoration", DecorationLayer);
        layers.put("Bombs", BombsLayer);
        layers.put("Objects", ObjectsLayer);
        layers.put("Characters", CharactersLayer);
        //endregion
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public java.util.Map<String, Layer> getLayers() {
        return layers;
    }

}