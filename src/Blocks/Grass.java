package Blocks;

import java.awt.*;

public class Grass extends SolidBlocks {
    public Grass(double x, double y) {
        super(x, y);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, 64, 64);
    }
}
