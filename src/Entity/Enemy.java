package Entity;

import Blocks.SolidBlocks;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Creature {


    private boolean notfalling = false;
    private boolean isThereABlockOnMyLeftSide = false;
    private boolean isThereABlockOnMyRightSide = false;
    private int speed = 2;

    public Enemy(Game game, double x, double y) {
        super(game, x, y);
        width = 60;
        height = 60;
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
    }

    private void checkBlocks() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();
            if (y + height > BlockY - 2 && ((BlockX > x && BlockX < x + width + 10))) {
                notfalling = true;
                y = BlockY - height;
                return;
            } else if (y + height > BlockY - 2 && ((BlockX + 64 > x && BlockX + 64 < x + width + 10))) {
                notfalling = true;
                y = BlockY - height;
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
            if ((y + height > BlockY && y + height < BlockY + 64
                    || y + height / 2 > BlockY && y + height / 2 < BlockY + 64
                    || y > BlockY && y < BlockY + 64)
                    && x + width + speed >= BlockX && !(x + width > BlockX + 64)) {
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
            if ((y + height > BlockY && y + height < BlockY + 64
                    || y + height / 2 > BlockY && y + height / 2 < BlockY + 64
                    || y > BlockY && y < BlockY + 64)
                    && x - speed <= BlockX + 64 && !(x < BlockX)) {
                isThereABlockOnMyLeftSide = true;
                isThereABlockOnMyRightSide = false;
                return;
            }
        }
    }


    public void move() {
        if (!isThereABlockOnMyLeftSide) {
            x = x - speed;
        } else {
            x = x + speed;
        }
    }


    private void falling() {
        if (!notfalling) {
            y = y + 10;
        }
    }

}
