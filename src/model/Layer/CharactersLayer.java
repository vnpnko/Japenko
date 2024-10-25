package model.Layer;

import model.Game;
import model.Tile.Empty;
import model.Tile.Monster;
import model.Tile.Player;

import java.util.ArrayList;

public class CharactersLayer extends Layer{
    public CharactersLayer(int size) {
        super(size);
        updateTiles(size);
        updateLayer(size);
    }

    @Override
    protected void updateTiles(int size) {
        ArrayList<Player> living_players = new ArrayList<>();
        ArrayList<Monster> living_monsters = new ArrayList<>();

        for(Player player : Game.players){
            if(player.isAlive()){
                living_players.add(player);
            }
        }
        // Position and add monsters directly to tiles
        for(Monster monster : Game.monsters){
            if(monster.isAlive()){
                living_monsters.add(monster);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean is_player_exists = false;
                for (Player player : living_players) {
                    if (player.getX() == j && player.getY() == i) {
                        is_player_exists = true;
                        this.tiles.add(player);
                    }
                }
                if (!is_player_exists) {
                    boolean is_monster_exists = false;
                    for (Monster monster : living_monsters) {
                        if (monster.getX() == j && monster.getY() == i) {
                            is_monster_exists = true;
                            this.tiles.add(monster);
                        }
                    }
                    if (!is_monster_exists) {
                        this.tiles.add(new Empty(j, i));
                    }
                }
            }
        }
    }
}
