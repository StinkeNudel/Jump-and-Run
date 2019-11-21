package Worlds;

import Blocks.SolidBlocks;
import Entity.Player;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MenuWorld extends World {

    private BufferedImage Image; //background image
    private Player player;

    /**
     * Constructor
     */
    public MenuWorld(Game game) {
        super(game);
        player = new Player(game, game.width/2, game.height/3);
        ArrayLists.player.add(player);
    }

    /**
     * World tick
     */
    @Override
    public void tick() {
        player.tick();
        cheat();

    }

    /**
     * World render
     */
    @Override
    public void render(Graphics g) {
        //render background
        g.drawImage(Image, 0, 0, null);
        player.render(g);

        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            m.render(g);
        }

    }

    public void cheat() {
        if (game.getKeyHandler().p) {
            TestWorld testWorld = new TestWorld(game);
            setWorld(testWorld);
        }
        if (game.getKeyHandler().o) {
                Tutorial tutorial = new Tutorial(game);
                setWorld(tutorial);
            }
        }
    }



