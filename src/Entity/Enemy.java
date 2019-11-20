package Entity;

import Blocks.SolidBlocks;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Creature {

    BufferedImage image = ImageLoader.loadImage("/worm1.png");


    private int enemyHeight = 60;
    private int enemyWidth = 60;
    private boolean notfalling = false;
    private boolean isThereABlockOnMyLeftSide = false;
    private boolean isThereABlockOnMyRightSide = false;
    private int speed = 2;
    private int animation;
    private int animationCounter;

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
        animationCounter++;

        if (animationCounter >= 3) {
            animation();
            animationCounter = 0;
        }

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

    public void animation() {
        switch (animation) {
            case 0:
                image = ImageLoader.loadImage("/worm1.png");
                animation++;
                break;
            case 1:
                image = ImageLoader.loadImage("/worm2.png");
                animation++;
                break;
            case 2:
                image = ImageLoader.loadImage("/worm3.png");
                animation++;
                break;
            case 3:
                image = ImageLoader.loadImage("/worm4.png");
                animation++;
                break;
            case 4:
                image = ImageLoader.loadImage("/worm5.png");
                animation++;
                break;
            case 5:
                image = ImageLoader.loadImage("/worm6.png");
                animation++;
                break;
            case 6:
                image = ImageLoader.loadImage("/worm7.png");
                animation++;
                break;
            case 7:
                image = ImageLoader.loadImage("/worm8.png");
                animation++;
                break;
            case 8:
                image = ImageLoader.loadImage("/worm9.png");
                animation++;
                break;
            case 9:
                image = ImageLoader.loadImage("/worm10.png");
                animation++;
                break;
            case 10:
                image = ImageLoader.loadImage("/worm9.png");
                animation++;
                break;
            case 11:
                image = ImageLoader.loadImage("/worm8.png");
                animation++;
                break;
            case 12:
                image = ImageLoader.loadImage("/worm7.png");
                animation++;
                break;
            case 13:
                image = ImageLoader.loadImage("/worm6.png");
                animation++;
                break;
            case 14:
                image = ImageLoader.loadImage("/worm5.png");
                animation++;
                break;
            case 15:
                image = ImageLoader.loadImage("/worm4.png");
                animation++;
                break;
            case 16:
                image = ImageLoader.loadImage("/worm3.png");
                animation++;
                break;
            case 17:
                image = ImageLoader.loadImage("/worm2.png");
                animation = 0;
                break;

        }

    }
}
