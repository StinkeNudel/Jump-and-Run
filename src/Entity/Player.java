package Entity;

import Blocks.SolidBlocks;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    BufferedImage image = ImageLoader.loadImage("/Player_Front.png");
    private Game game;
    private int playerWidth = 96, playerHeight = 117;
    private boolean notfalling = false;
    private boolean jump = false;
    private double beforeJumpY;
    private int jumpSpeed = 40;
    private int speed = 5;
    private boolean movingRight, movingLeft;

    public Player(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick() {
        input();
        checkBlocks();
        gravity();
        jump();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, playerWidth, playerHeight, null);
    }

    private void input() {
        if (game.getKeyHandler().space) {
            if (notfalling && !jump) {
                jump = true;
                beforeJumpY = y;
            }
        }
        if (game.getKeyHandler().a) {
            movingLeft = true;
            checkLeft();
            if (movingLeft) {
                x = x - speed;
                movingLeft = false;
            }
        }
        if (game.getKeyHandler().d) {
            movingRight = true;
            checkRight();
            if (movingRight) {
                x = x + speed;
                movingRight = false;
            }
        }
    }


    private void checkBlocks() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();
            if (y + playerHeight > BlockY - 2 && ((BlockX > x && BlockX < x + playerWidth))) {
                notfalling = true;
                return;
            } else if (y + playerHeight > BlockY - 2 && ((BlockX + 64 > x && BlockX + 64 < x + playerWidth))) {
                notfalling = true;
                return;
            } else {
                notfalling = false;
            }
        }
    }

    private void checkRight() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();
            if ((y + playerHeight > BlockY && y + playerHeight < BlockY + 64
                    || y + playerHeight / 2 > BlockY && y + playerHeight / 2 < BlockY + 64
                    || y > BlockY && y < BlockY + 64)
                    && x + playerWidth + speed >= BlockX && !(x + playerWidth > BlockX + 64)) {
                movingRight = false;
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
            if ((y + playerHeight > BlockY && y + playerHeight < BlockY + 64
                    || y + playerHeight/2 > BlockY && y + playerHeight/2 < BlockY + 64
                    || y > BlockY && y < BlockY + 64)
                    && x - speed <= BlockX + 64 && !(x < BlockX)) {
                movingLeft = false;
                return;
            }
        }
    }

    private void jump() {
        if (jump) {
            y = y - jumpSpeed;

            if (y < beforeJumpY - 100) {
                jumpSpeed = 20;

            }

            if (y < beforeJumpY - 200) {
                jumpSpeed = 40;
                jump = false;
            }
        }
    }

    private void gravity() {
        if (!notfalling) {
            y = y + 2;
        }
    }
}

