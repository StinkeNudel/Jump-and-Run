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
    private final BufferedImage background = ImageLoader.loadImage("/Menu/MenuBackground.png");
    private BufferedImage cursor = ImageLoader.loadImage("/Menu/Cursor1.png");

    private int checkpoint;
    private int menuPosition = 0;
    private boolean onlyOnce = true;

    private int cursorAnimation = 0;



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
        g.drawImage(background,0,0, game.width, game.height, null);
        renderMenu(g);

    }


    private void renderMenu(Graphics g) {
        cursorAnimation(g);
        switch (menuPosition){
            case 0:
                g.drawImage(newWorldButtonSelected, 21 * Game.blockSize, 4 * Game.blockSize, 8 * Game.blockSize, 2*Game.blockSize, null);
                g.drawImage(loadWorldButton, 21 * Game.blockSize, 6 * Game.blockSize + (int)(0.3* Game.blockSize), 8 * Game.blockSize,2*Game.blockSize, null);
                g.drawImage(newWorldButton, 21 * Game.blockSize, 8 * Game.blockSize + (int)(0.6* Game.blockSize), 8 * Game.blockSize , 2*Game.blockSize, null);
break;
            case 1:
                g.drawImage(newWorldButton, 21 * Game.blockSize, 4 * Game.blockSize, 8 * Game.blockSize, 2*Game.blockSize, null);
                g.drawImage(loadWorldButtonSelected, 21 * Game.blockSize, 6 * Game.blockSize + (int)(0.3* Game.blockSize), 8 * Game.blockSize,2*Game.blockSize, null);
                g.drawImage(newWorldButton, 21 * Game.blockSize, 8 * Game.blockSize + (int)(0.6* Game.blockSize), 8 * Game.blockSize, 2*Game.blockSize, null);
                break;
            case 2:
                g.drawImage(newWorldButton, 21 * Game.blockSize, 4 * Game.blockSize, 8 * Game.blockSize, 2*Game.blockSize, null);
                g.drawImage(loadWorldButton, 21 * Game.blockSize, 6 * Game.blockSize + (int)(0.3* Game.blockSize), 8 * Game.blockSize,2*Game.blockSize, null);
                g.drawImage(newWorldButtonSelected, 21 * Game.blockSize, 8 * Game.blockSize + (int)(0.6* Game.blockSize), 8 * Game.blockSize, 2*Game.blockSize, null);
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
        if (game.getKeyHandler().enter) {
            switch (menuPosition) {
                case 0://New Game
                    ArrayLists.trees.clear();
                    ArrayLists.cloud1s.clear();
                    ArrayLists.cloud2s.clear();
                    ArrayLists.mountains.clear();
                    World1 tutorial = new World1(game);
                    setWorld(tutorial);
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
    }


    public void cursorAnimation(Graphics g){
        switch (cursorAnimation) {
            case 0:
                cursor = ImageLoader.loadImage("/Menu/Cursor1.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int)(3.5 * Game.blockSize) + menuPosition * (int)(2.3 * Game.blockSize), (int)(Game.blockSize*1.3), (int)(Game.blockSize/1.2), null);
                cursorAnimation++;
                break;
            case 1:
                cursor = ImageLoader.loadImage("/Menu/Cursor2.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int)(3.5 * Game.blockSize) + menuPosition * (int)(2.3 * Game.blockSize), (int)(Game.blockSize*1.3), (int)(Game.blockSize/1.2), null);
                cursorAnimation++;
                break;
            case 2:
            case 6:
                cursor = ImageLoader.loadImage("/Menu/Cursor3.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int)(3.5 * Game.blockSize) + menuPosition * (int)(2.3 * Game.blockSize), (int)(Game.blockSize*1.3), (int)(Game.blockSize/1.2), null);
                cursorAnimation++;
                break;
            case 3:
            case 5:
                cursor = ImageLoader.loadImage("/Menu/Cursor4.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int)(3.5 * Game.blockSize) + menuPosition * (int)(2.3 * Game.blockSize), (int)(Game.blockSize*1.3), (int)(Game.blockSize/1.2), null);
                cursorAnimation++;
                break;
            case 4: cursor = ImageLoader.loadImage("/Menu/Cursor5.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int)(3.5 * Game.blockSize) + menuPosition * (int)(2.3 * Game.blockSize), (int)(Game.blockSize*1.3), (int)(Game.blockSize/1.2), null);
                cursorAnimation++;
                break;
            case 7:
                cursor = ImageLoader.loadImage("/Menu/Cursor2.png");
                g.drawImage(cursor, 19 * Game.blockSize, (int)(3.5 * Game.blockSize) + menuPosition * (int)(2.3 * Game.blockSize), (int)(Game.blockSize*1.3), (int)(Game.blockSize/1.2), null);
                cursorAnimation = 0;
                break;


        }
    }
}



