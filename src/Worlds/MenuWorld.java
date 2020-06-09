package Worlds;

import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

public class MenuWorld extends World {
    private BufferedImage newWorldButton = ImageLoader.loadImage("/Menu/newWorldButton.png");
    private BufferedImage loadWorldButton = ImageLoader.loadImage("/Menu/loadWorldButton.png");
    private BufferedImage newWorldButtonSelected = ImageLoader.loadImage("/Menu/newWorldButtonSelected.png");
    private BufferedImage loadWorldButtonSelected = ImageLoader.loadImage("/Menu/loadWorldButtonSelected.png");
    private BufferedImage background = ImageLoader.loadImage("/Menu/MenuBackground.png");

    private int checkpoint;
    private int menuPosition = 0;
    private boolean onlyOnce = true;



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
        g.fillRect(13 * Game.blockSize, 4 * Game.blockSize + menuPosition * 2 * Game.blockSize, Game.blockSize, Game.blockSize);
        switch (menuPosition){
            case 0:
                g.drawImage(newWorldButtonSelected, 15 * Game.blockSize, 4 * Game.blockSize, 8 * Game.blockSize, 2*Game.blockSize, null);
                g.drawImage(loadWorldButton, 15 * Game.blockSize, 6 * Game.blockSize, 8 * Game.blockSize,2*Game.blockSize, null);
break;
            case 1:
                g.drawImage(newWorldButton, 15 * Game.blockSize, 4 * Game.blockSize, 8 * Game.blockSize, 2*Game.blockSize, null);
                g.drawImage(loadWorldButtonSelected, 15 * Game.blockSize, 6 * Game.blockSize, 8 * Game.blockSize,2*Game.blockSize, null);
                break;
            case 2:
                g.drawImage(newWorldButton, 15 * Game.blockSize, 4 * Game.blockSize, 8 * Game.blockSize, 2*Game.blockSize, null);
                g.drawImage(loadWorldButton, 15 * Game.blockSize, 6 * Game.blockSize, 8 * Game.blockSize,2*Game.blockSize, null);

        }

        g.drawString("Test World", 15 * Game.blockSize, 8 * Game.blockSize + (int) (0.5 * Game.blockSize));
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
}



