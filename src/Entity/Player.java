package Entity;

import Blocks.SolidBlocks;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;
import Worlds.Defeat;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Main.ArrayLists.enemys;
import static Main.ArrayLists.player;

public class Player extends Entity {
    BufferedImage image = ImageLoader.loadImage("/Player_Front.png");
    private int playerWidth = 96, playerHeight = 117;
    private boolean notfalling = false;
    private boolean jump = false;
    private double beforeJumpY;
    private int jumpSpeed = 40;
    private int speed = 5;
    private boolean movingRight, movingLeft;
    private boolean touchingEnemy = false;

    int life = 10;

    public Player(Game game, double x, double y) {
        super(game, x, y);
        this.game = game;
    }

    @Override
    public void tick() {
        input();
        checkBlocks();
        gravity();
        jump();
        lowerHealth();
        die();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), playerWidth, playerHeight, null);
    }

    private void input() {
        if (game.getKeyHandler().space) {
            if (notfalling && !jump) {
                jump = true;
                beforeJumpY = y;
            }
        }
        if (game.getKeyHandler().a) {
            image = ImageLoader.loadImage("/Player_Left.png");
            movingLeft = true;
            checkLeft();
            if (movingLeft) {
                x = x - speed;
                game.getGameCamera().move(-speed, 0);
                movingLeft = false;
            }
        }

        if (game.getKeyHandler().d) {
            image = ImageLoader.loadImage("/Player_Right.png");
            movingRight = true;
            checkRight();
            if (movingRight) {
                x = x + speed;
                game.getGameCamera().move(speed, 0);
                movingRight = false;
            }
        }

        if ((!game.getKeyHandler().d && !game.getKeyHandler().a) || game.getKeyHandler().d && game.getKeyHandler().a) {
            image = ImageLoader.loadImage("/Player_Front.png");
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
                y = BlockY - playerHeight;
                return;
            } else if (y + playerHeight > BlockY - 2 && ((BlockX + 64 > x && BlockX + 64 < x + playerWidth))) {
                notfalling = true;
                y = BlockY - playerHeight;
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
                    || y + playerHeight / 2 > BlockY && y + playerHeight / 2 < BlockY + 64
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
            y = y + 5;
        }
    }

    public void lowerHealth() {
        int noTouchingEnemies = 0;
        int offset = 25;
        double EnemyUpX, EnemyUpY;
        double EnemyDownX, EnemyDownY;
        double EnemyRightX, EnemyRightY;
        double EnemyLeftX, EnemyLeftY;
        ArrayList enemies = ArrayLists.getEnemys();
        for (int w = 0; w < enemies.size(); w++) {
            Enemy m = (Enemy) enemies.get(w);

            EnemyUpX = m.getX() + 64;
            EnemyUpY = m.getY() + offset;
            EnemyDownX = m.getX() + 64;
            EnemyDownY = m.getY() + 128 - offset;
            EnemyRightX = m.getX() + 128 - offset;
            EnemyRightY = m.getY() + 64;
            EnemyLeftX = m.getX() + offset;
            EnemyLeftY = m.getY() + 64;

            if (EnemyUpX > x && EnemyUpX < x + playerWidth
                    && EnemyUpY > y && EnemyUpY < y + playerWidth ||
                    EnemyDownX > x && EnemyDownX < x + playerWidth
                            && EnemyDownY > y && EnemyDownY < y + playerWidth ||
                    EnemyRightX > x && EnemyRightX < x + playerWidth
                            && EnemyRightY > y && EnemyRightY < y + playerWidth
                    || EnemyLeftX > x && EnemyLeftX < x + playerWidth
                    && EnemyLeftY > y && EnemyLeftY < y + playerWidth) {
                if (!touchingEnemy) {
                    life--;
                    touchingEnemy = true;
                }

            } else {
                noTouchingEnemies++;
                if (enemys.size() == noTouchingEnemies) {
                    touchingEnemy = false;
                }
            }
        }
    }

    public void die() {
        if (life == 0) {
            Defeat defeat = new Defeat(game);
            World.setWorld(defeat);
        }
    }
}