package model;

import model.Audio.MusicPlayer;
import model.Audio.SFXPlayer;
import model.Tile.*;
import view.*;

import java.util.ArrayList;

public class Game {
    public static Map map;
    public static SFXPlayer sfxPlayer;
    public static MusicPlayer musicPlayer;
    public static ArrayList<Player> players;
    public static ArrayList<Bomb> bombs;
    public static ArrayList<Explosion> explosions;
    public static ArrayList<Box> boxes;
    public static ArrayList<Treasure> treasures;
    public static ArrayList<Monster> monsters;
    public static int number_of_players;
    public static int number_of_rounds;
    public static int current_round;
    public static boolean is_paused = true;
    public static boolean is_round_finished = false;
    public static boolean is_finished = false;
    public static boolean sound_on = true;

    //constructor
    public Game(){
        musicPlayer = new MusicPlayer();
        sfxPlayer = new SFXPlayer();
        StartScreen_GUI game = new StartScreen_GUI();
    }

    public static void RefreshMode(){
        players = new ArrayList<>();
        bombs = new ArrayList<>();
        explosions = new ArrayList<>();
        boxes = new ArrayList<>();
        treasures = new ArrayList<>();
        monsters = new ArrayList<>();
        number_of_players = 2;
        map = new Map("SmallMap");
        number_of_rounds = 1;
        current_round = 1;
        is_paused = true;
        is_finished = false;
        is_round_finished = false;
    }

    public static void refreshForRound(){
        for(Player p : players){
            p.refreshForNewRound();
        }
        for(Bomb bomb : bombs){
            if(bomb instanceof NormalBomb){
                ((NormalBomb) bomb).getTimer().cancel();
            }
        }
        for(Explosion exp : explosions){
            exp.getOwner_bomb().getExp_timer().cancel();
        }
        bombs.clear();
        explosions.clear();
        boxes.clear();
        treasures.clear();
        //region >> set boxes on new map
        for(int i = 3; i < Game.map.getSize() - 2; i+=2){
            for(int j = 3; j < Game.map.getSize() - 2; j+=2){
                boxes.add(new Box(i, j));
            }
        }
        //endregion
        is_paused = false;
        is_round_finished = false;
    }

    public static int getNumberOfAlivePlayers(){
        int alivePlayers = 0;
        for(Player player : Game.players){
            if(player.isAlive()){
                alivePlayers++;
            }
        }
        return alivePlayers;
    }
}