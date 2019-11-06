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
    private int playerWidth = 100, playerHeight = 100;
    private boolean notfalling = false;
    private boolean jump = false;
    private double beforeJumpY;
   private int jumpSpeed = 40;
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
            x = x - 5;
        }
        if (game.getKeyHandler().d) {
            x = x + 5;
        }
    }

    private void checkBlocks() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();

            if (y + playerHeight >= BlockY && BlockX > x && BlockX < x + playerWidth) {
                notfalling = true;
                return;
            } else {
                notfalling = false;
            }
        }


    }

    private void jump() {
        int jumpHeight = (int) (beforeJumpY - y);

        if (jump) {
            y = y - jumpSpeed;
            if (y < beforeJumpY - 200){
                jump = false;
                jumpSpeed = jumpSpeed -10;
            }
        }
    }

    private void gravity() {
        if (!notfalling) {
            y = y + 10;
            jumpSpeed = 40;
        }
    }
}
