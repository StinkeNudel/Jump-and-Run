package Entity;

import Blocks.SolidBlocks;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Creature {

    BufferedImage image = ImageLoader.loadImage("/noodle.jpg");


    private int enemyHeight = 60;
    private int enemyWidth = 60;
    private boolean notfalling = false;
    boolean isThereABlockOnMyLeftSide = false;

    public Enemy(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        checkBlocks();
        falling();
        move();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), enemyWidth, enemyHeight, null);


    }

    private void checkBlocks() {
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

            if (x < BlockX + 60 && x + enemyWidth > BlockX && y < BlockY + 60 && y + enemyHeight > BlockY) {
                isThereABlockOnMyLeftSide = true;
            }
        }
    }



    public void move() {
        if(!isThereABlockOnMyLeftSide) {
            x = x - 2;
        } else {
            x = x + 2;
            isThereABlockOnMyLeftSide = false;
        }
    }


    private void falling() {
        if(!notfalling) {
            y = y + 10;
        }
    }
}
