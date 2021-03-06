package Worlds;

import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

public class MenuWorld extends World {
    private final BufferedImage newWorldButton = ImageLoader.loadImage("/Menu/newWorldButton.png");
    private final BufferedImage loadWorldButton = ImageLoader.loadImage("/Menu/loadWorldButton.png");
    private final BufferedImage newWorldButtonSelected = ImageLoader.loadImage("/Menu/newWorldButtonSelected.png");
    private final BufferedImage loadWorldButtonSelected = ImageLoader.loadImage("/Menu/loadWorldButtonSelected.png");
    private final BufferedImage optionsButton = ImageLoader.loadImage("/Menu/optionsButton.png");
    private final BufferedImage optionsButtonSelected = ImageLoader.loadImage("/Menu/optionsButtonSelected.png");
    private final BufferedImage background = ImageLoader.loadImage("/Menu/MenuBackground.png");
    private BufferedImage cursor = ImageLoader.loadImage("/Menu/Cursor1.png");

    private BufferedImage player = ImageLoader.loadImage("/Player/player_right_up_openEyes.png"); // Image of the Player


    private int checkpoint;
    private int menuPosition = 0;
    private boolean onlyOnce = true;

    private int cursorAnimation = 0;
    int cursorAnimationCounter = 0;
    int animationRight = 0;
    int playerCounter = 0;
    private int worldChangeAlpha = 1;
    private boolean worldWillChange = false;
    private boolean changeInput = true;

    /**
     * Constructor
     */
    public MenuWorld(Game game) {
        super(game);
    }

    /**
     * World tick
     */
    @Override
    public void tick() {
        input();
    }

    /**
     * World render
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(background, 0, 0, game.width, game.height, null);
        renderMenu(g);
        playerAnimationRight(g);
        renderWorldChange(g);

    }


    private void renderMenu(Graphics g) {

        cursorAnimationCounter++;
        cursorAnimation(g);
        if (cursorAnimationCounter >= 5) {
            cursorAnimationCounter = 0;
        }

        switch (menuPosition) {
            case 0:
                g.drawImage(newWorldButtonSelected, 21 * Game.blockSize, 3 * Game.blockSize, 8 * Game.blockSize, 2 * Game.blockSize, null);
                g.drawImage(loadWorldButton, 21 * Game.blockSize, 5 * Game.blockSize + (int) (0.3 * Game.blockSize), 8 * Game.blockSize, 2 * Game.blockSize, null);
                g.drawImage(optionsButton, 21 * Game.blockSize, 7 * Game.blockSize + (int) (0.6 * Game.blockSize), 8 * Game.blockSize, 2 * Game.blockSize, null);
                break;
            case 1:
                g.drawImage(newWorldButton, 21 * Game.blockSize, 3 * Game.blockSize, 8 * Game.blockSize, 2 * Game.blockSize, null);
                g.drawImage(loadWorldButtonSelected, 21 * Game.blockSize, 5 * Game.blockSize + (int) (0.3 * Game.blockSize), 8 * Game.blockSize, 2 * Game.blockSize, null);
                g.drawImage(optionsButton, 21 * Game.blockSize, 7 * Game.blockSize + (int) (0.6 * Game.blockSize), 8 * Game.blockSize, 2 * Game.blockSize, null);
                break;
            case 2:
                g.drawImage(newWorldButton, 21 * Game.blockSize, 3 * Game.blockSize, 8 * Game.blockSize, 2 * Game.blockSize, null);
                g.drawImage(loadWorldButton, 21 * Game.blockSize, 5 * Game.blockSize + (int) (0.3 * Game.blockSize), 8 * Game.blockSize, 2 * Game.blockSize, null);
                g.drawImage(optionsButtonSelected, 21 * Game.blockSize, 7 * Game.blockSize + (int) (0.6 * Game.blockSize), 8 * Game.blockSize, 2 * Game.blockSize, null);
        }
    }

    /**
     * loads the SafeFile
     */
    private void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\SaveFile"));
            checkpoint = Integer.parseInt(br.readLine());
            br.close();
        } catch (Exception e) {
        }
    }

    /**
     * KeyInput
     */
    public void input() {
        if (changeInput) {
            if (onlyOnce) {
                if (game.getKeyHandler().down && menuPosition < 2) {
                    menuPosition++;
                    onlyOnce = false;
                }
                if (game.getKeyHandler().up && menuPosition > 0) {
                    menuPosition--;
                    onlyOnce = false;
                }
            } else if (!game.getKeyHandler().up && !game.getKeyHandler().down) {
                onlyOnce = true;
            }
        }
        if (game.getKeyHandler().enter) {
                worldWillChange = true;
                changeInput = true;
            }
        }

    private void renderWorldChange(Graphics g) {
        if (worldWillChange) {
            if (worldChangeAlpha < 252) {
                Color color = new Color(0, 0, 0, worldChangeAlpha);
                g.setColor(color);
                g.fillRect(0, 0, game.width, game.height);
                worldChangeAlpha +=2;
            }
            if (worldChangeAlpha >= 252) {
                changeWorld();
            }
        }
    }

    private void changeWorld() {
        switch (menuPosition) {
            case 0://New Game
                ArrayLists.trees.clear();
                ArrayLists.cloud1s.clear();
                ArrayLists.cloud2s.clear();
                ArrayLists.mountains.clear();
                Level1 level1 = new Level1(game);
                setWorld(level1);
                break;
            case 1: //Load Game
                ArrayLists.trees.clear();
                ArrayLists.cloud1s.clear();
                ArrayLists.cloud2s.clear();
                ArrayLists.mountains.clear();
                loadFile();
                switch (checkpoint) {
                    case 0:
                        World1 world1 = new World1(game);
                        setWorld(world1);
                        break;
                    case 1:
                        TestWorld testWorld = new TestWorld(game);
                        setWorld(testWorld);
                        break;
                }
                break;
            case 2: //Options
                ArrayLists.trees.clear();
                ArrayLists.cloud1s.clear();
                ArrayLists.cloud2s.clear();
                ArrayLists.mountains.clear();
                TestWorld testWorld = new TestWorld(game);
                setWorld(testWorld);
                break;
        }
    }

    public void cursorAnimation(Graphics g) {
        switch (cursorAnimation) {
            case 0:
                cursor = ImageLoader.loadImage("/Menu/Cursor1.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int) (3.5 * Game.blockSize) + menuPosition * (int) (2.3 * Game.blockSize), (int) (Game.blockSize * 1.3), (int) (Game.blockSize / 1.2), null);
                if (cursorAnimationCounter >= 5) {
                    cursorAnimation++;
                }
                break;
            case 1:
                cursor = ImageLoader.loadImage("/Menu/Cursor2.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int) (3.5 * Game.blockSize) + menuPosition * (int) (2.3 * Game.blockSize), (int) (Game.blockSize * 1.3), (int) (Game.blockSize / 1.2), null);
                if (cursorAnimationCounter >= 5) {
                    cursorAnimation++;
                }
                break;
            case 2:
            case 6:
                cursor = ImageLoader.loadImage("/Menu/Cursor3.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int) (3.5 * Game.blockSize) + menuPosition * (int) (2.3 * Game.blockSize), (int) (Game.blockSize * 1.3), (int) (Game.blockSize / 1.2), null);
                if (cursorAnimationCounter >= 5) {
                    cursorAnimation++;
                }
                break;
            case 3:
            case 5:
                cursor = ImageLoader.loadImage("/Menu/Cursor4.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int) (3.5 * Game.blockSize) + menuPosition * (int) (2.3 * Game.blockSize), (int) (Game.blockSize * 1.3), (int) (Game.blockSize / 1.2), null);
                if (cursorAnimationCounter >= 5) {
                    cursorAnimation++;
                }
                break;
            case 4:
                cursor = ImageLoader.loadImage("/Menu/Cursor5.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int) (3.5 * Game.blockSize) + menuPosition * (int) (2.3 * Game.blockSize), (int) (Game.blockSize * 1.3), (int) (Game.blockSize / 1.2), null);
                if (cursorAnimationCounter >= 5) {
                    cursorAnimation++;
                }
                break;
            case 7:
                cursor = ImageLoader.loadImage("/Menu/Cursor2.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int) (3.5 * Game.blockSize) + menuPosition * (int) (2.3 * Game.blockSize), (int) (Game.blockSize * 1.3), (int) (Game.blockSize / 1.2), null);
                if (cursorAnimationCounter >= 5) {
                    cursorAnimation = 0;
                }
                break;


        }
    }

    private void playerAnimationRight(Graphics g) {
        playerCounter++;
        switch (animationRight) {
            case 0:
                player = ImageLoader.loadImage("/Player/player_right_up_openEyes.png");
                if (playerCounter > 12) {
                    animationRight++;
                    playerCounter = 0;
                }
                break;
            case 1:
                player = ImageLoader.loadImage("/Player/player_right_down_openEyes.png");
                if (playerCounter > 12) {
                    animationRight++;
                    playerCounter = 0;
                }
                break;
            case 2:
            case 4:
                player = ImageLoader.loadImage("/Player/player_right_up_halfClosedEyes.png");
                if (playerCounter > 12) {
                    animationRight++;
                    playerCounter = 0;
                }
                break;
            case 3:
                player = ImageLoader.loadImage("/Player/player_down_noEyes.png");
                if (playerCounter > 12) {
                    animationRight++;
                    playerCounter = 0;
                }
                break;
            case 5:
                player = ImageLoader.loadImage("/Player/player_right_down_openEyes.png");
                if (playerCounter > 12) {
                    animationRight = 0;
                    playerCounter = 0;
                }
                break;
        }
        g.drawImage(player, 11 * Game.blockSize, 8 * Game.blockSize, 2 * Game.blockSize, 4 * Game.blockSize, null);
    }
}



