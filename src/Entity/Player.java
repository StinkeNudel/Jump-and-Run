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

public class Player extends Creature {
    BufferedImage image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
    private int playerWidth = 68, playerHeight = 120;
    private boolean notfalling = false;
    private boolean jump = false;
    private double beforeJumpY;
    private int jumpSpeed = 40;
    private int speed = 5;
    private boolean movingRight, movingLeft;
    private boolean touchingEnemy = false;
    private int offsetBeforeJump;
    private boolean jumpedAgainstBlock;
    private HealthBar healthBar;
    private boolean movedRight;

    int jumpAnimation = 0, animationJump = 0, waitForJump = 0;
    int animationLeft = 0;
    int animationRight = 0;
    int animationStandLeft = 0, animationStandRight = 0;
    int animationCounterLeft = 0, animationCounterRight = 0, animationCounterStandLeft = 0, animationCounterStandRight = 0;

    /**
     * Constructor
     *
     * @param game Game Object
     * @param x    X-Coordinate
     * @param y    Y-XCoordinate
     */
    public Player(Game game, double x, double y) {
        super(game, x, y);
        this.game = game;
        health = 10;
        healthBar = new HealthBar();
    }


    /**
     * ticks the Player
     */
    public void tick() {
        input();
        checkBlocks();
        gravity();
        jump();
        lowerHealth();
        die();
        checkUp();

        //HealthBar
        healthBar.tick();
    }


    /**
     * renders the Player
     *
     * @param g Graphics Object
     */
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), playerWidth, playerHeight, null);
        healthBar.render(g);
    }


    /**
     * Keyboard input
     */
    private void input() {
        if (game.getKeyHandler().space) {
            if (notfalling && !jump) {
                offsetBeforeJump = (int) game.getGameCamera().getyOffset();
                jump = true;
                beforeJumpY = y;
            }
        }
        if (game.getKeyHandler().a) {
            animationCounterLeft++;
            if (animationCounterLeft >= 3) {
                animationLeft();
                animationCounterLeft = 0;
            }
            movingLeft = true;
            checkLeft();
            if (movingLeft) {
                x = x - speed;
                game.getGameCamera().move(-speed, 0);
                movingLeft = false;
                movedRight = false;
            }
        }

        if (game.getKeyHandler().d) {
            animationCounterRight++;
            if (animationCounterRight >= 3) {
                animationRight();
                animationCounterRight = 0;
            }
            movingRight = true;
            checkRight();
            if (movingRight) {
                x = x + speed;
                game.getGameCamera().move(speed, 0);
                movingRight = false;
                movedRight = true;
            }
        }

        if ((!game.getKeyHandler().d && !game.getKeyHandler().a) || game.getKeyHandler().d && game.getKeyHandler().a) {
            if (!movedRight) {
                animationCounterStandLeft++;
                if (animationCounterStandLeft >= 7) {
                    animationStandLeft();
                    animationCounterStandLeft = 0;
                }
            } else {
                animationCounterStandRight++;
                if (animationCounterStandRight >= 7) {
                    animationStandRight();
                    animationCounterStandRight = 0;
                }

            }
        }
    }


    /**
     * checks Blocks below
     */
    private void checkBlocks() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();
            if (y + playerHeight > BlockY - 2 && ((BlockX > x && BlockX < x + playerWidth)) && y + playerHeight < BlockY + 64) {
                notfalling = true;
                jumpedAgainstBlock = false;
                y = BlockY - playerHeight;
                return;
            } else if (y + playerHeight > BlockY - 2 && ((BlockX + 64 > x && BlockX + 64 < x + playerWidth)) && !(y + playerHeight > BlockY + 64)) {
                notfalling = true;
                jumpedAgainstBlock = false;
                y = BlockY - playerHeight;
                return;
            } else {
                notfalling = false;
            }
        }
    }


    /**
     * checks Blocks above
     */
    private void checkUp() {
        double BlockX, BlockY;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            BlockX = m.getX();
            BlockY = m.getY();
            if (jump && y < BlockY + 64 && y > BlockY && ((BlockX > x && BlockX < x + playerWidth))) {
                jump = false;
                y = BlockY + 64 + 1;
                jumpedAgainstBlock = true;
                game.getGameCamera().setyOffset(offsetBeforeJump);
            }
            if (jump && y < BlockY + 64 && y > BlockY && ((BlockX + 64 > x && BlockX + 64 < x + playerWidth))) {
                jump = false;
                y = BlockY + 64 + 1;
                jumpedAgainstBlock = true;
                game.getGameCamera().setyOffset(offsetBeforeJump);

            }
        }
    }


    /**
     * checks Blocks right
     */
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


    /**
     * check Blocks left
     */
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


    /**
     * Player will jump
     */
    private void jump() {
        if (jump) {

            animationJump++;
            if (animationJump >= 3) {
                jumpAnimation();
            }

            y = y - jumpSpeed;
            game.getGameCamera().move(0, -jumpSpeed);

            if (y < beforeJumpY - 100) {
                jumpSpeed = 20;

            }

            if (y < beforeJumpY - 200) {
                jumpSpeed = 40;
                jump = false;
            }
        }
    }


    /**
     * player moves down to create gravity
     */
    private void gravity() {
        if (!notfalling) {
            y = y + 5;
            fallAnimation();
            if (!jumpedAgainstBlock) {
                game.getGameCamera().move(0, 5);
            }
        }
    }


    /**
     * Player will loose lifes if he touches an Enemy
     */
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
                    health--;
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


    /**
     * Player will die if life equals 0
     */
    public void die() {
        if (health == 0) {
            Defeat defeat = new Defeat(game);
            World.setWorld(defeat);
        }
    }


    //___________________________________________________________________________________________________________________________________________________________________________________________________________________
    //___________________________________________________________________________________________________________________________________________________________________________________________________________________
    //HEALTHBAR CLASS
    public class HealthBar {
        private int startHealth; //Player health at the beginning
        private int barHeight = 60; //height of the bar
        private int barWidth = 400; //width of the bar
        private int barFillPerLive; //width of the bar per health point
        private int barCounter = 0; //counter for the health

        /**
         * Constructor
         */
        public HealthBar() {
            this.startHealth = 10;
            barFillPerLive = barWidth / startHealth;
        }

        /**
         * Bar tick
         */
        public void tick() {
            if (!(barCounter == startHealth)) {
                barCounter = startHealth - health;
            }
        }

        /**
         * draws the bar
         */
        public void render(Graphics g) {
            g.drawRect(100, 100, barWidth, barHeight);
            g.setColor(Color.GRAY);
            g.fillRect(100, 100, barWidth, barHeight);
            g.setColor(Color.RED);
            g.fillRect(100, 100, barWidth - barCounter * barFillPerLive, barHeight);
        }

    }

    /**
     * animations
     */

    public void animationLeft() {
        switch (animationLeft) {
            case 0:
                image = ImageLoader.loadImage("/Player/player_left_up_openEyes.png");
                animationLeft++;
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_left_left_leg.png");
                animationLeft++;
                break;
            case 2:
                image = ImageLoader.loadImage("/Player/player_left_up_openEyes.png");
                animationLeft++;
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_left_right_leg.png");
                animationLeft = 0;
                break;
        }

    }

    public void animationRight() {
        switch (animationRight) {
            case 0:
                image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
                animationRight++;
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_right_left_leg.png");
                animationRight++;
                break;
            case 2:
                image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
                animationRight++;
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_right_right_leg.png");
                animationRight = 0;
                break;
        }

    }

    public void animationStandLeft() {
        switch (animationStandLeft) {
            case 0:
                image = ImageLoader.loadImage("/Player/player_left_up_openEyes.png");
                animationStandLeft++;
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_left_down_openEyes.png");
                animationStandLeft++;
                break;
            case 2:
                image = ImageLoader.loadImage("/Player/player_left_up_halfOpenEyes.png");
                animationStandLeft++;
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_left_down_noEyes.png");
                animationStandLeft++;
                break;
            case 4:
                image = ImageLoader.loadImage("/Player/player_left_up_halfOpenEyes.png");
                animationStandLeft++;
                break;
            case 5:
                image = ImageLoader.loadImage("/Player/player_left_down_openEyes.png");
                animationStandLeft = 0;
                break;


        }
    }

    public void animationStandRight() {
        switch (animationStandRight) {
            case 0:
                image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
                animationStandRight++;
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_right_down_openEyes.png");
                animationStandRight++;
                break;
            case 2:
                image = ImageLoader.loadImage("/Player/player_right_up_halfClosedEyes.png");
                animationStandRight++;
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_down_noEyes.png");
                animationStandRight++;
                break;
            case 4:
                image = ImageLoader.loadImage("/Player/player_right_up_halfClosedEyes.png");
                animationStandRight++;
                break;
            case 5:
                image = ImageLoader.loadImage("/Player/player_right_down_openEyes.png");
                animationStandRight = 0;
                break;


        }
    }

    public void jumpAnimation() {
        switch (jumpAnimation) {
            case 0:
                image = ImageLoader.loadImage("/Player/jump1.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 1:
                image = ImageLoader.loadImage("/Player/jump2.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 2:
                image = ImageLoader.loadImage("/Player/jump3.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 3:
                image = ImageLoader.loadImage("/Player/jump4.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 4:
                image = ImageLoader.loadImage("/Player/jump5.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 5:
                image = ImageLoader.loadImage("/Player/jump6.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 6:
                image = ImageLoader.loadImage("/Player/jump7.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 7:
                image = ImageLoader.loadImage("/Player/jump8.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 8:
                image = ImageLoader.loadImage("/Player/jump9.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
        }
    }

    public void fallAnimation() {
        switch (jumpAnimation) {
            case 9:
                image = ImageLoader.loadImage("/Player/jump10.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 10:
                image = ImageLoader.loadImage("/Player/jump11.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 11:
                image = ImageLoader.loadImage("/Player/jump12.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 12:
                image = ImageLoader.loadImage("/Player/jump13.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 13:
                image = ImageLoader.loadImage("/Player/jump14.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    jumpAnimation = 0;
                    waitForJump = 0;
                    break;
                }

        }
    }
}

//___________________________________________________________________________________________________________________________________________________________________________________________________________________
//___________________________________________________________________________________________________________________________________________________________________________________________________________________

