package Entity.Doors;

import Entity.Entity;
import GFX.ImageLoader;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends Entity {

    private BufferedImage door = ImageLoader.loadImage("/door.png");
    private BufferedImage cityGate = ImageLoader.loadImage("/CityGate.png");
    private BufferedImage graveDoor = ImageLoader.loadImage("/door.png");


    public String type;


    public Door(Game game, double x, double y, String type) {
        super(game, x, y);
        this.type = type;

        width = game.blockSize;
        height = 2 * game.blockSize;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if (type.equals("GraveDoor")) {
            g.drawImage(graveDoor, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (type.equals("Door")) {
            g.drawImage(door, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (type.equals("CityGate")) {
            g.drawImage(cityGate, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        }
    }
}