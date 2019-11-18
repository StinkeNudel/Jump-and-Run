package Entity;

import Blocks.SolidBlocks;
import Main.ArrayLists;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Creature {

    int enemyWidth = 60;
    int enemyHeight = 60;
    boolean notfalling = false;

    public Enemy(double x, double y) {
        super(x, y);

        health = 2;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void walk() {

    }

    public void checkBlocks() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();

            if (y + enemyHeight >= BlockY - 1 && ((BlockX > x && BlockX < x + enemyWidth))) {
                notfalling = true;
                return;
            } else {
                notfalling = false;
            }
        }
    }
}
