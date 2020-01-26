package Worlds;

import Blocks.SolidBlocks;
import Blocks.Stone;
import Entity.Doors.DoorSaveRoom;
import Entity.Player;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class SaveWorld extends World {

    private Player player;
    private DoorSaveRoom doorSaveRoom;

    /**
     * Constructor
     *
     * @param game Game Object
     */
    public SaveWorld(Game game) {
        super(game);
        generateBlocks();

        player = new Player(game, game.width / 2, game.height - 221);
        ArrayLists.player.add(player);


        doorSaveRoom = new DoorSaveRoom(game, game.width / 2 + 500, game.height - 225);
        game.getGameCamera().setyOffset(300);
        saveGame();
    }

    /**
     * ticks the world
     */
    public void tick() {
        player.tick();
        doorSaveRoom.tick();
    }

    /**
     * renders the world
     *
     * @param g Graphics Object
     */
    public void render(Graphics g) {
        doorSaveRoom.render(g);
        player.render(g);
        renderBlocks(g);
    }

    private void renderBlocks(Graphics g){
        ArrayList solidBlocks = ArrayLists.getSolidBlocks();
        for (int w = 0; w < solidBlocks.size(); w++) {
            SolidBlocks m = (SolidBlocks) solidBlocks.get(w);
            if (m.x > player.x - 1100 && m.x < player.x + 1000) {
                m.render(g);
            }
        }
    }

    /**
     * generates the Bocks in the World
     */
    public void generateBlocks() {
        int BlockX = game.width / 2 - 500, BlockY = game.height - 100;

        for (int i = 0; i < 19; i++) {
            Stone z = new Stone(game, BlockX, BlockY);
            ArrayLists.solidBlocks.add(z);
            BlockX = BlockX + 64;
        }
    }

    /**
     * saves in SafeFile
     */
    private void saveGame() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Workspace\\Jump-and-Run\\src\\SaveFile"));
            bw.write("1");
            bw.close();
        } catch (Exception e) {
        }
    }
}