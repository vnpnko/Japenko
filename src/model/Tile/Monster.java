package model.Tile;

import java.util.Random;

import model.EventListener.BombExplodeListener;
import model.EventListener.MonsterDieListener;
import model.Game;
public class Monster extends Tile implements BombExplodeListener {
    private boolean is_alive;
    private int speed;
    private Direction direction;
    private MonsterDieListener monsterDieListener;
    Random random = new Random();
    enum Direction {
        UP, DOWN, LEFT, RIGHT, STOP // Also add a stop state
    }

    public Monster(String visual) {
        super(1, 3);  // Initialize position at (0, 0)
        this.destructible = true;
        this.visual = visual;  // Set visual from parameter
        this.speed = 1;  // Set default speed
        this.direction = Direction.STOP;
        is_alive = true;// Initial direction set to stop
    }

    public boolean isAlive() {
        return is_alive;
    }
    public void die(){
        is_alive = false;
        fireMonsterDieListener();
    }

    // Update the monster's movement logic
    public void updateMovement() {
        // Change direction randomly or at a decision point
        if (random.nextInt(100) < 20) {  // 20% chance to randomly change direction
            changeDirectionRandomly();
        } else if (shouldChangeDirection()) {  // Checks if there is an obstacle
            changeDirectionRandomly();
        }

        // Additional logic to periodically change direction independently of obstacles
        if (random.nextInt(100) < 10) {  // 10% chance to change direction voluntarily
            changeDirectionRandomly();
        }
    }

    // Method to change the monster's direction randomly
    void changeDirectionRandomly() {
        Direction[] directions = Direction.values();
        this.direction = directions[random.nextInt(directions.length)];
    }

    boolean shouldChangeDirection() {
        // This should be replaced with actual collision detection logic
        // Example: pseudo code for collision detection
        // return map.isObstacleAt(this.x + dx[this.direction], this.y + dy[this.direction]);
        return random.nextBoolean();  // Randomly simulates obstacle detection
    }

    // Method to process the movement of the monster
    public void move() {
        // Calculate new position based on direction
        int newX = this.getX();
        int newY = this.getY();
        switch (this.direction) {
            case UP: newY -= speed; break;
            case DOWN: newY += speed; break;
            case LEFT: newX -= speed; break;
            case RIGHT: newX += speed; break;
            default: return; // Do not move if STOP or invalid direction
        }

        // Check for collisions with the bounds of the game field or immovable objects
        if (isValidPosition(newX, newY)) {
            this.setX(newX);
            this.setY(newY);
            for(Player player:Game.players){
                if (player.getX() == x && player.getY() == y) {
                    // Player is at the position the monster is trying to move to
                    System.out.println(this.getVisual() + " killed " + player.getVisual());
                    player.die(); // Here, you would call the method that handles player death
                }
            }
        } else {
            // Change direction if the move is not valid
            changeDirectionRandomly();
        }
    }

    private boolean isPlayerAtPosition(int x, int y) {
        for (Player player : Game.players) {
            if (player.getX() == x && player.getY() == y) {
                // Player is at the position the monster is trying to move to
                player.die(); // Here, you would call the method that handles player death
                return true; // Collision with player detected
            }
        }
        return false; // No player at the new position
    }

    boolean isValidPosition(int x, int y) {
        // Check if the position is outside the map bounds
        if (x < 0 || x >= Game.map.getSize() || y < 0 || y >= Game.map.getSize()) {
            return false;
        }

        // Check for collision with Bricks, Blocks, and Treasures
        for (Tile tile : Game.map.getLayers().get("Objects").getTiles()) {
            if ((tile instanceof Brick || tile instanceof Block || tile instanceof Box) && tile.getX() == x && tile.getY() == y) {
                return false; // Collision detected, position is not valid
            }
        }

        for(Tile characters_tile : Game.map.getLayers().get("Characters").getTiles()) {
            if((characters_tile instanceof Monster) && characters_tile.getX() == x && characters_tile.getY() == y) {
                return false;
            }
        }

        // No collision detected, position is valid
        return true;
    }

    public void fireMonsterDieListener(){
        this.monsterDieListener.monsterDie();
    }
    public void setMonsterDieListener(MonsterDieListener listener){
        this.monsterDieListener = listener;
    }

    // Method to set the direction
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Method to get the direction
    public Direction getDirection() {
        return direction;
    }

    // Method to handle interactions with other tiles
    public void interact(Tile tile) {
        // Implement interaction logic here
        // For example, check if tile is an instance of Player and perform specific actions
        if (tile instanceof Player) {
            // Assuming Player has a method to handle death
            ((Player)tile).die();
            System.out.println("Player has been killed by a monster.");
        } else if (tile instanceof Monster) {
            // Both monsters change direction to avoid overlap or conflict
            this.setDirection(Direction.values()[random.nextInt(Direction.values().length)]);
            ((Monster)tile).setDirection(Direction.values()[random.nextInt(Direction.values().length)]);
            System.out.println("Two monsters have encountered and changed their directions.");
        }
        else if (tile instanceof Brick) {
            // The monster changes direction upon hitting a brick
            this.setDirection(Direction.values()[random.nextInt(Direction.values().length)]);
            System.out.println("Monster hit a brick and changed direction.");
        }
    }

    // Method to set the monster's speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Method to get the monster's speed
    public int getSpeed() {
        return speed;
    }

    @Override
    public void bombExploded() {
        for(Explosion explosion : Game.explosions){
            if(explosion.getX() == this.x && explosion.getY() == this.y){
                System.out.println("Die " + this.getVisual());
                die();
            }
        }
    }



    @Override
    public void bombFinishExplosion() {

    }
    @Override
    public void bombDestroyedBox() {

    }
}


