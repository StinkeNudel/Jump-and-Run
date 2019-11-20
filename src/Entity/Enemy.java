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
    boolean isThereABlockOnMyRightSide = false;
    int speed = 2;

    public Enemy(Game game, double x, double y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        checkBlocks();
        falling();
        move();
        checkLeft();
        checkRight();

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
            if (y + enemyHeight > BlockY - 2 && ((BlockX > x && BlockX < x + enemyWidth + 10))) {
                notfalling = true;
                y = BlockY - enemyHeight;
                return;
            } else if (y + enemyHeight > BlockY - 2 && ((BlockX + 64 > x && BlockX + 64 < x + enemyWidth + 10))) {
                notfalling = true;
                y = BlockY - enemyHeight;
                return;
            }
            }
        notfalling = false;
        }


    private void checkRight() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();
            if ((y + enemyHeight > BlockY && y + enemyHeight < BlockY + 64
                    || y + enemyHeight / 2 > BlockY && y + enemyHeight / 2 < BlockY + 64
                    || y > BlockY && y < BlockY + 64)
                    && x + enemyWidth + speed >= BlockX && !(x + enemyWidth > BlockX + 64)) {
                isThereABlockOnMyRightSide = true;
                isThereABlockOnMyLeftSide = false;
                return;
            }
        }
    }

    private void checkLeft() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();
            if ((y + enemyHeight > BlockY && y + enemyHeight < BlockY + 64
                    || y + enemyHeight / 2 > BlockY && y + enemyHeight / 2 < BlockY + 64
                    || y > BlockY && y < BlockY + 64)
                    && x - speed <= BlockX + 64 && !(x < BlockX)) {
                isThereABlockOnMyLeftSide = true;
                isThereABlockOnMyRightSide = false;
                return;
            }
        }
    }



    public void move() {
        if(!isThereABlockOnMyLeftSide) {
            x = x - speed;
        } else {
            x = x + speed;
        }
    }


    private void falling() {
        if(!notfalling) {
            y = y + 10;
        }
    }
}
