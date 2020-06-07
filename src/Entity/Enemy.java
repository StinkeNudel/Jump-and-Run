package Entity;

import Blocks.SolidBlocks;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Creature {


    private boolean falling = false;
    private boolean isThereABlockOnMyLeftSide = false;
    private boolean isThereABlockOnMyRightSide = false;
    private double speed = game.blockSize*0.03125;

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
    }

    private void checkBlocks() {
        falling = true;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (Object solidBlock : solidBlocks) {
            SolidBlocks m = (SolidBlocks) solidBlock;

            if (this.getBounds().intersects(m.getBounds())) {
                falling = false;
                if (y + game.blockSize*0.921875 != m.y) {
                    y = m.y - game.blockSize*0.921875;
                }
            }
        }
    }


    private void checkRight() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (Object solidBlock : solidBlocks) {
            SolidBlocks m = (SolidBlocks) solidBlock;
            BlockX = m.getX();
            BlockY = m.getY();
            if ((y + height > BlockY && y + height < BlockY + game.blockSize
                    || y + height / 2 > BlockY && y + height / 2 < BlockY + game.blockSize
                    || y > BlockY && y < BlockY + game.blockSize)
                    && x + width + speed >= BlockX && !(x + width > BlockX + game.blockSize)) {
                isThereABlockOnMyRightSide = true;
                isThereABlockOnMyLeftSide = false;
                return;
            }
        }
    }

    private void checkLeft() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (Object solidBlock : solidBlocks) {
            SolidBlocks m = (SolidBlocks) solidBlock;
            BlockX = m.getX();
            BlockY = m.getY();
            if ((y + height > BlockY && y + height < BlockY + game.blockSize
                    || y + height / 2 > BlockY && y + height / 2 < BlockY + game.blockSize
                    || y > BlockY && y < BlockY + game.blockSize)
                    && x - speed <= BlockX + game.blockSize && !(x < BlockX)) {
                isThereABlockOnMyLeftSide = true;
                isThereABlockOnMyRightSide = false;
                return;
            }
        }
    }


    private void move() {
        if (!isThereABlockOnMyLeftSide) {
            x = x - speed;
        } else {
            x = x + speed;
        }
    }


    private void falling() {
        if (falling) {
            y = y + game.blockSize*0.15625;
        }
    }

}
