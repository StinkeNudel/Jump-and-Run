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

public class Player extends Creature {
    private BufferedImage image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
    private HealthBar healthBar;
    private boolean falling = false;
    private boolean jump = false;
    private int speed = 5;
    private boolean movingRight, movingLeft;
    private boolean movedRight;
    private int xOnScreen, yOnScreen;
    private int jumpCounter;
    private long hitDelay = System.currentTimeMillis();

    private int jumpAnimation = 0, animationJump = 0, waitForJump = 0;
    private int animationLeft = 0;
    private int animationRight = 0;
    private int animationStandLeft = 0, animationStandRight = 0;
    private int animationCounterLeft = 0, animationCounterRight = 0, animationCounterStandLeft = 0, animationCounterStandRight = 0;

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
        width = 68;
        height = 120;
        health = 10;
        healthBar = new HealthBar();
    }

    /**
     * ticks the Player
     */
    public void tick() {
        input();
        checkBlockCollision();
        moveCamera();
        gravity();
        jump();
        lowerHealth();
        die();

        //HealthBar
        healthBar.tick();
    }

    /**
     * renders the Player
     *
     * @param g Graphics Object
     */
    public void render(Graphics g) {
        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        healthBar.render(g);
    }

    /**
     * Keyboard input
     */
    private void input() {
        if (game.getKeyHandler().space) {
            if (!falling && !jump) {
                jump = true;
            }
        }
        if (game.getKeyHandler().a) {
            animationCounterLeft++;
            if (animationCounterLeft >= 3) {
                animationLeft();
                animationCounterLeft = 0;
            }
            movingLeft = true;
            checkBlockCollision();
            if (movingLeft) {
                x = x - speed;
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
            checkBlockCollision();
            if (movingRight) {
                x = x + speed;
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

    private void checkBlockCollision() {
        falling = true;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);

            if (this.getBoundsDown().intersects(m.getBounds())) {
                falling = false;
            }
            if (movingRight) {
                if (this.getBoundsRight().intersects(m.getBounds())) {
                    movingRight = false;
                }
            }
            if (movingLeft) {
                if (this.getBoundsLeft().intersects(m.getBounds())) {
                    movingLeft = false;
                }
            }
            if (jump) {
                if (this.getBoundsTop().intersects(m.getBounds())) {
                    jump = false;
                    falling = true;
                }
            }
        }
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + 5, (int) y + 5, width, height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x - 5, (int) y, width, height);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) x, (int) y + 1, width, height / 2);
    }

    public Rectangle getBoundsDown() {
        return new Rectangle((int) x, (int) y + height / 2 + 5, width, height / 2);
    }


    /**
     * Player will jump
     */
    private void jump() {
        if (jump) {

            animationJump++;
            if (animationJump >= 3) {
                jumpAnimation();
                image = ImageLoader.loadImage("/Player/jump9.png");
            }
            if (jumpCounter < 5) {
                y = y - 30;
            } else if (jumpCounter < 10) {
                y = y - 20;
            } else if (jumpCounter < 15) {
                y = y - 10;
            } else if (jumpCounter < 20) {
                y = y - 5;
            }
            jumpCounter++;
        }

        if (jumpCounter >= 20) {
            jumpCounter = 0;
            jump = false;
        }
    }

    /**
     * player moves down to create gravity
     */
    private void gravity() {
        if (falling) {
            y = y + 8;
            fallAnimation();
            image = ImageLoader.loadImage("/Player/jump14.png");
        }
    }

    /**
     * Player will loose lifes if he touches an Enemy
     */
    public void lowerHealth() {
        ArrayList enemies = ArrayLists.getEnemys();
        for (int w = 0; w < enemies.size(); w++) {
            Enemy m = (Enemy) enemies.get(w);
            if (this.getBounds().intersects(m.getBounds())) {
                if (System.currentTimeMillis() - hitDelay > 1000) {
                    health--;
                    hitDelay = System.currentTimeMillis();
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

    private void moveCamera() {
        xOnScreen = (int) (x - game.getGameCamera().getxOffset());
        yOnScreen = (int) (y - game.getGameCamera().getyOffset());
        if (yOnScreen <= 300) {
            game.getGameCamera().move(0, -3);
        }
        if (yOnScreen >= 700 && 900 > yOnScreen) {
            game.getGameCamera().move(0, 3);
        } else if (yOnScreen >= 900) {
            game.getGameCamera().move(0, 8);
        }

        if (xOnScreen >= 1020) {
            game.getGameCamera().move(5, 0);

        }
        if (xOnScreen <= 900) {
            game.getGameCamera().move(-5, 0);
        }
    }

    /**
     * animations
     */

    private void animationLeft() {
        switch (animationLeft) {
            case 0:
            case 2:
                image = ImageLoader.loadImage("/Player/player_left_up_openEyes.png");
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_left_left_leg.png");
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_left_right_leg.png");
                animationLeft = 0;
                break;
        }
        animationLeft++;
    }

    private void animationRight() {
        switch (animationRight) {
            case 0:
            case 2:
                image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_right_left_leg.png");
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_right_right_leg.png");
                animationRight = 0;
                break;
        }
        animationRight++;

    }

    private void animationStandLeft() {
        switch (animationStandLeft) {
            case 0:
                image = ImageLoader.loadImage("/Player/player_left_up_openEyes.png");
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_left_down_openEyes.png");
                break;
            case 2:
            case 4:
                image = ImageLoader.loadImage("/Player/player_left_up_halfOpenEyes.png");
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_left_down_noEyes.png");
                break;
            case 5:
                image = ImageLoader.loadImage("/Player/player_left_down_openEyes.png");
                animationStandLeft = 0;
                break;


        }
        animationStandLeft++;
    }

    private void animationStandRight() {
        switch (animationStandRight) {
            case 0:
                image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
                break;
            case 1:
                image = ImageLoader.loadImage("/Player/player_right_down_openEyes.png");
                break;
            case 2:
            case 4:
                image = ImageLoader.loadImage("/Player/player_right_up_halfClosedEyes.png");
                break;
            case 3:
                image = ImageLoader.loadImage("/Player/player_down_noEyes.png");
                break;
            case 5:
                image = ImageLoader.loadImage("/Player/player_right_down_openEyes.png");
                animationStandRight = 0;
                break;


        }
        animationStandRight++;
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
}