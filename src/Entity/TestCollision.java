package Entity;

import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.util.ArrayList;

public class TestCollision extends Entity {
    int width = 64;
    int height = 64;

    /**
     * Coordinate
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-Coordinate
     */
    public TestCollision(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        ArrayList player = ArrayLists.getPlayer();
        for (int w = 0; w < player.size(); w++) {
            Player m = (Player) player.get(w);
            if (m.getBounds().intersects(this.getBounds())){
                System.out.println("YEEEEEEEEEEEEEESSSSSS");
            }
        }
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)  (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
