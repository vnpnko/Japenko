package model.Tile;

import java.util.Random;
import model.Game;

public class ChaserMonster extends Monster {
    public ChaserMonster(String visual) {
        super(visual);
        this.setSpeed(3);
    }

    @Override
    protected void changeDirectionRandomly() {
        Player closestPlayer = findClosestPlayer();
        if (closestPlayer != null) {
            this.setDirection(getDirectionTowardsPlayer(closestPlayer));
        } else {
            super.changeDirectionRandomly();
        }
    }

    private Player findClosestPlayer() {
        Player closestPlayer = null;
        int minDistance = Integer.MAX_VALUE;
        for (Player player : Game.players) {
            if (player.isAlive()) {
                int distance = Math.abs(player.getX() - this.getX()) + Math.abs(player.getY() - this.getY());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPlayer = player;
                }
            }
        }
        return closestPlayer;
    }

    private Direction getDirectionTowardsPlayer(Player player) {
        int dx = player.getX() - this.getX();
        int dy = player.getY() - this.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? Direction.RIGHT : Direction.LEFT;
        } else {
            return dy > 0 ? Direction.DOWN : Direction.UP;
        }
    }

    @Override
    protected boolean shouldChangeDirection() {
        return super.shouldChangeDirection(); // 基本のロジックを利用する
    }

    @Override
    public void move() {
        int newX = this.getX() + getDX();
        int newY = this.getY() + getDY();

        if (isValidPosition(newX, newY)) {
            this.setX(newX);
            this.setY(newY);
            for(Player player:Game.players) {
                if (player.getX() == x && player.getY() == y) {
                    player.die();
                }
            }
        } else {
            changeDirectionRandomly();
        }
    }

    private int getDX() {
        return this.getDirection() == Direction.RIGHT ? 1 : this.getDirection() == Direction.LEFT ? -1 : 0;
    }

    private int getDY() {
        return this.getDirection() == Direction.DOWN ? 1 : this.getDirection() == Direction.UP ? -1 : 0;
    }
}
