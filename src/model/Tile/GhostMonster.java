package model.Tile;
import model.Game;

public class GhostMonster extends Monster {
    public GhostMonster(String visual) {
        super(visual);
        this.setSpeed(1);
    }

    @Override
    protected boolean shouldChangeDirection() {
        for (Bomb bomb : Game.bombs) {
            if (isNextToBomb(bomb)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNextToBomb(Bomb bomb) {
        int nextX = this.getX() + getDX();
        int nextY = this.getY() + getDY();
        return bomb.getX() == nextX && bomb.getY() == nextY;
    }

    private int getDX() {
        return this.getDirection() == Direction.RIGHT ? 1 : this.getDirection() == Direction.LEFT ? -1 : 0;
    }

    private int getDY() {
        return this.getDirection() == Direction.DOWN ? 1 : this.getDirection() == Direction.UP ? -1 : 0;
    }

    @Override
    protected boolean isValidPosition(int x, int y) {
        if (x < 0 || y < 0 || x >= Game.map.getSize() || y >= Game.map.getSize()) {
            return false;
        }
        for (Bomb bomb : Game.bombs) {
            if (bomb.getX() == x && bomb.getY() == y) {
                return false;
            }
        }

        for (Tile tile : Game.map.getLayers().get("Objects").getTiles()) {
            if ((tile instanceof Brick) && tile.getX() == x && tile.getY() == y) {
                return false;
            }
        }

        for(Tile characters_tile : Game.map.getLayers().get("Characters").getTiles()) {
            if((characters_tile instanceof Monster) && characters_tile.getX() == x && characters_tile.getY() == y) {
                return false;
            }
        }
        return true;
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
}
