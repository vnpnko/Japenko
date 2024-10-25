package model.Tile;

import java.util.Random;
import model.Game;

public class ConfusedMonster extends Monster {
    private final Random random = new Random();

    public ConfusedMonster(String visual) {
        super(visual);
        this.setSpeed(1);
    }

    @Override
    protected void changeDirectionRandomly() {
        if(random.nextInt(100) < 30) {
            Direction[] directions = Direction.values();
            setDirection(directions[random.nextInt(directions.length)]);
        } else {
            super.changeDirectionRandomly();
        }
    }

    @Override
    protected boolean shouldChangeDirection() {
        return random.nextBoolean();
    }
}
