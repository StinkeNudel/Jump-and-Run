package Worlds;

import Entity.Door;
import Main.Game;

import java.awt.*;

public class SaveWorld extends World {

    private Door door;

    public SaveWorld(Game game) {
        super(game);

        door = new Door(game, game.width/2, game.height/2);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
