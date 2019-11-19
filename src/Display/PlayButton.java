package Display;

import Entity.Entity;
import Main.Main;

import java.awt.*;
import Main.Game;
import Worlds.TestWorld;


public class PlayButton extends Entity {

    private Game game;

    /**
     * Constructor
     */
    public PlayButton(Game game, double x, double y) {
        super(x, y);
        this.game = game;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

}
