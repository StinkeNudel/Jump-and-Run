package Entity;

import Blocks.BackBlock;
import Blocks.Item;
import Blocks.SolidBlocks;
import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;
import Worlds.Defeat;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Creature {
    private BufferedImage image = ImageLoader.loadImage("/Player/player_right_up_openEyes.png"); // Image of the Player
    private HealthBar healthBar; // HealthBar of the Player
    private boolean falling = false; //decides if the Player is falling or not
    private boolean jump = false; //shows if the Player is jumping atm (not including the fall after the jump)
    private boolean movingRight, movingLeft; //the direction the player is moving at
    private boolean movedRight; //true if the player moved right before he stopped
    private boolean fullLadder = false; //true if the player stand at a ladder
    private boolean topLadder = false; //true if the player stand at a ladder
    private int jumpCounter; //times the duration of the jump
    private long hitDelay = System.currentTimeMillis(); //delay the taken damage

    private int jumpAnimation = 0, animationJump = 0, waitForJump = 0; //JumpAnimation
    private int animationLeft = 0; //Walking left animation
    private int animationRight = 0; //walking right animation
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
        width = game.blockSize;
        height = game.blockSize * 2;
        health = 10;
        healthBar = new HealthBar();
        movedRight = true;
    }

    /**
     * ticks the Player
     */
    public void tick() {
        checkBlockCollision();
        checkLadder();
        input();
        moveCamera();
        movement();
        checkEnemy();

        //HealthBar
        healthBar.tick();
    }

    /**
     * renders the Player
     *
     * @param g Graphics Object
     */
    public void render(Graphics g) {
        if (movingRight || movedRight) {
            g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(image, (int) (x - game.getGameCamera().getxOffset() + game.blockSize / 2), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        }
        healthBar.render(g);
        //g.drawRect((int) (getBoundsTop().x - game.getGameCamera().getxOffset()), (int) (getBoundsTop().y - game.getGameCamera().getyOffset()), getBoundsTop().width, getBoundsTop().height);
        //g.drawRect((int) (getBoundsDown().x - game.getGameCamera().getxOffset()), (int) (getBoundsDown().y - game.getGameCamera().getyOffset()), getBoundsDown().width, getBoundsDown().height);
        //g.setColor(Color.GREEN);
        //g.drawRect((int) (getBoundsLeft().x - game.getGameCamera().getxOffset()), (int) (getBoundsLeft().y - game.getGameCamera().getyOffset()), getBoundsLeft().width, getBoundsLeft().height);
        //g.drawRect((int) (getBoundsRight().x - game.getGameCamera().getxOffset()), (int) (getBoundsRight().y - game.getGameCamera().getyOffset()), getBoundsRight().width, getBoundsRight().height);
        //g.drawRect((int) (getBoundsLadderDown().x - game.getGameCamera().getxOffset()), (int) (getBoundsLadderDown().y - game.getGameCamera().getyOffset()), getBoundsLadderDown().width, getBoundsLadderDown().height);
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
            movingLeft = true;
            movedRight = false;
        }

        if (game.getKeyHandler().d) {
            movingRight = true;
            movingLeft = false;
        }

        if (game.getKeyHandler().w) {
            if (fullLadder) {
                y = y - game.blockSize * 5 / 64;
            }
        }
        if (game.getKeyHandler().s) {
            if (topLadder || topLadder) {
                y = y + game.blockSize * 5 / 64;
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

        if (game.getKeyHandler().e) {
            collectItem();
            System.out.println("schwurbelSCHWURBEL");
        }
    }

    /**
     * checks for Blocks collision
     * always checks for blocks below
     * checks for Blocks left and right if moving
     * checks for Blocks above if jumping
     */
    private void checkBlockCollision() {
        falling = true;
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (Object solidBlock : solidBlocks) {
            SolidBlocks m = (SolidBlocks) solidBlock;

            if (this.getBoundsDown().intersects(m.getBounds())) {
                falling = false;
                if (y + (height - 1) != m.y) {
                    y = m.y - (height - 1);
                }
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
        ArrayList backBlocks = ArrayLists.getBackBlocks();
        for (Object backBlock : backBlocks) {
            BackBlock m = (BackBlock) backBlock;
            if (m.type.equals("ladder")) {
                if (this.getBoundsLadderDown().intersects(m.getBounds())) {
                    falling = false;
                }
            }

        }

    }

    private void checkLadder() {
        ArrayList backBlocks = ArrayLists.getBackBlocks();
        for (Object backBlock : backBlocks) {
            BackBlock m = (BackBlock) backBlock;
            if (m.type.equals("ladder") && this.getBoundsDown().intersects(m.getBounds()) && m.type.equals("ladder") && this.getBoundsLadderDown().intersects(m.getBounds())) {
                fullLadder = true;
                topLadder = true;
                falling = false;
                return;
            } else {
                fullLadder = false;
            }
            if (m.type.equals("ladder") && getBoundsLadderDown().intersects(m.getBounds())) {
                topLadder = true;
                falling = false;
                return;
            } else {
                topLadder = false;
            }
        }
    }


    /**
     * check if player standing next to an Item, when click 'E'
     */

    public void collectItem() {
        ArrayList items = ArrayLists.getItems();
        for (Object item : items) {
            Item q = (Item) item;
            if (this.getBoundsRight().intersects(q.getBounds())) {
                items.remove(q);
                System.out.println("schwurbel");
            }
        }
    }

    /**
     * Player will loose health if he touches an Enemy
     */
    private void checkEnemy() {
        ArrayList enemies = ArrayLists.getEnemys();
        for (Object enemy : enemies) {
            Enemy m = (Enemy) enemy;
            if (this.getBounds().intersects(m.getBounds())) {
                if (System.currentTimeMillis() - hitDelay > 1000) {
                    health--;
                    hitDelay = System.currentTimeMillis();
                    if (health == 0) {
                        Defeat defeat = new Defeat(game);
                        World.setWorld(defeat);
                    }
                }
            }
        }
    }

    private void moveCamera() {
        int xOnScreen = (int) (x - game.getGameCamera().getxOffset());
        int yOnScreen = (int) (y - game.getGameCamera().getyOffset());
        if (yOnScreen <= game.blockSize * 4.6875) {
            game.getGameCamera().move(0,  -game.blockSize * (float)0.046875);
        }
        if (yOnScreen >= game.blockSize*10.9375 && game.blockSize*14.0625 > yOnScreen) {
            game.getGameCamera().move(0, game.blockSize * (float)0.046875);
        } else if (yOnScreen >= game.blockSize*14.0625) {
            game.getGameCamera().move(0, game.blockSize/8);
        }

        if (xOnScreen >= game.blockSize*15.9375) {
            game.getGameCamera().move(game.blockSize* (float)0.078125, 0);

        }
        if (xOnScreen <= game.blockSize*14.0625) {
            game.getGameCamera().move(-game.blockSize* (float)0.078125, 0);
        }
    }


    //Movement

    /**
     * handles the movement of the Player
     * gravity()
     * jump()
     * checks if movingRight = true or movingLeft = true
     */
    private void movement() {
        gravity();
        jump();

        double speed = game.blockSize*0.078125;
        if (movingRight) {
            animationCounterRight++;
            if (animationCounterRight >= 3) {
                if (!falling) {
                    animationRight();
                }
                animationCounterRight = 0;
            }
            checkBlockCollision();
            if (movingRight) {
                x = x + speed;
                movingRight = false;
                movedRight = true;
            }
        }

        if (movingLeft) {
            animationCounterLeft++;
            if (animationCounterLeft >= 3) {
                if (!falling) {
                    animationLeft();
                }
                animationCounterLeft = 0;
            }
            movingLeft = true;
            movedRight = false;
            checkBlockCollision();
            if (movingLeft) {
                x = x - speed;
                movingLeft = false;
                movedRight = false;

            }
        }
    }

    /**
     * Player will jump
     */
    private void jump() {
        if (jump && !fullLadder) {

            animationJump++;
            if (animationJump >= 3) {
                if (movingRight || movedRight) {
                    jumpAnimation();
                    //image = ImageLoader.loadImage("/Player/jump9.png");
                } else if (movingLeft || !movedRight) {
                    jumpAnimationLeft();
                    //image = ImageLoader.loadImage("/Player/jump9Left.png");
                }

            }
            if (jumpCounter < 5) {
                y = y - game.blockSize*0.46875;
            } else if (jumpCounter < 10) {
                y = y - game.blockSize*0.3125;
            } else if (jumpCounter < 15) {
                y = y - game.blockSize*0.15625;
            } else if (jumpCounter < 20) {
                y = y - game.blockSize*0.078125;
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
            y = y + game.blockSize/8;
            if (movingRight || movedRight) {
                fallAnimation();
                image = ImageLoader.loadImage("/Player/jump14.png");
            } else if (movingLeft || !movedRight) {
                fallAnimationLeft();
                image = ImageLoader.loadImage("/Player/jump14Left.png");
            }
        }
    }


    //Hitboxes
    private Rectangle getBoundsRight() {
        return new Rectangle((int) x + game.width / 30, (int) y + game.height / 72, width - game.width / 32, height - game.height / 36);
    }

    private Rectangle getBoundsLeft() {
        return new Rectangle((int) x + game.width / 64, (int) y + game.height / 72, width - game.width / 32, height - game.height / 36);
    }

    private Rectangle getBoundsTop() {
        return new Rectangle((int) x + game.width / 55, (int) y + 1, width - game.width / 48, height / 2 - game.height / 36);
    }

    private Rectangle getBoundsDown() {
        return new Rectangle((int) x + game.width / 55, (int) y + height / 2 + 5, width - game.width / 48, height / 2 - 5);
    }

    private Rectangle getBoundsLadderDown() {
        return new Rectangle((int) x + game.width / 55, (int) y + height / 2 + 10, width - game.width / 48, height / 2 - 5);
    }


    //Animations
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

    private void jumpAnimation() {
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

    private void jumpAnimationLeft() {
        switch (jumpAnimation) {
            case 0:
                image = ImageLoader.loadImage("/Player/jump1Left.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 1:
                image = ImageLoader.loadImage("/Player/jump2Left.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 2:
                image = ImageLoader.loadImage("/Player/jump3Left.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 3:
                image = ImageLoader.loadImage("/Player/jump4Left.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 4:
                image = ImageLoader.loadImage("/Player/jump5Left.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 5:
                image = ImageLoader.loadImage("/Player/jump6Left.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 6:
                image = ImageLoader.loadImage("/Player/jump7Left.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 7:
                image = ImageLoader.loadImage("/Player/jump8Left.png");
                waitForJump++;

                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 8:
                image = ImageLoader.loadImage("/Player/jump9Left.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
        }
    }

    private void fallAnimation() {
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

    private void fallAnimationLeft() {
        switch (jumpAnimation) {
            case 9:
                image = ImageLoader.loadImage("/Player/jump10Left.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 10:
                image = ImageLoader.loadImage("/Player/jump11Left.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 11:
                image = ImageLoader.loadImage("/Player/jump12Left.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 12:
                image = ImageLoader.loadImage("/Player/jump13Left.png");
                waitForJump++;
                if (waitForJump >= 9) {
                    jumpAnimation++;
                    waitForJump = 0;
                    break;
                }
            case 13:
                image = ImageLoader.loadImage("/Player/jump14Left.png");
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
        private int barWidth = (int)(game.blockSize*3.59375); //width of the bar
        private int barFillPerLive; //width of the bar per health point
        private int barCounter = 0; //counter for the health
        private BufferedImage barImage = ImageLoader.loadImage("/HealthBar.png");

        /**
         * Constructor
         */
        private HealthBar() {
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
            int barHeight = game.blockSize;
            //height of the bar
            g.drawRect((int)(game.blockSize*1.5625), (int)(game.blockSize*1.5625), barWidth, barHeight);
            g.setColor(Color.GRAY);
            g.fillRect((int)(game.blockSize*1.5625), (int)(game.blockSize*1.5625), barWidth, barHeight);
            Color barRed = new Color(164, 0, 0);
            g.setColor(barRed);
            g.fillRect((int)(game.blockSize*1.5625), (int)(game.blockSize*1.5625), barWidth - barCounter * barFillPerLive, barHeight);
            g.drawImage(barImage, (int)(game.blockSize*1.09375), (int)(game.blockSize*1.25), (int)(game.blockSize*4.53125), (int)(game.blockSize*1.5625), null);
        }

    }
}