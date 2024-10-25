package model.Tile;

import model.Game;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.EventListener.BombExplodeListener;

public class NormalBomb extends Bomb implements Runnable{
    public static int time_until_explode = 4;
    protected int time = time_until_explode;
    protected final Timer timer = new Timer();

    public NormalBomb(int x, int y, Player owner) {
        super(x, y, owner);
        this.visual = "NormalBomb.png";

        // Create a timer and schedule a task to decrement time every second.
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if(!Game.is_paused){
//                    time--;
//                    if (time <= 0) {//if the time is over
//                        explode();
//                        timer.cancel(); // stop timer
//                    }
//                }
//            }
//        }, 1000, 1000);// Execute the task once 1 second later, and then every second thereafter.
    }

    @Override
    public void run() {
        try{
            while(time > 0 && !Game.is_round_finished){
                Thread.sleep(1000);
                if(!Game.is_paused){
                    time--;
                }
            }
            if(!Game.is_round_finished){
                explode();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Bomb countdown thread interrupted: " + e.getMessage());
        }
    }

    //region >> getter/setter
    public Timer getTimer(){
        return timer;
    }

    //endregion
}