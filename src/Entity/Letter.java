package Entity;

import GFX.ImageLoader;
import Input.MouseHandler;
import Main.Game;
import Worlds.ScrollWorld;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Letter extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Scroll/Scroll_Open-12.png.png");

    private Game game;
    private int scrollImage;

    public Letter(Game game, double x, double y) {
        super(game, x, y);
        this.game = game;
        width = (int)(game.blockSize*1.625);
        height = game.blockSize*2;
    }

    public void tick() {
        move();
        readMe();
    }

    public void render(Graphics g) {

        g.drawImage(image, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }

    private void move() {

        /**
         * Scroll fliegt in die Mitte und rollt sich dann aus
         */
        if (x >= game.width / 2 - game.blockSize*3.046875) {
            x = x - game.blockSize*0.0625;
        } else {
            switch (scrollImage) { //switch ist für die Scroll-Animation
                case 0:
                    width = (int)(game.blockSize*2.03125);
                    height = (int)(game.blockSize*2.5);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-11.png.png");
                    scrollImage++;
                    break;
                case 1:
                    width = (int)(game.blockSize*2.4375);
                    height = (int)(game.blockSize*3.03125);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-10.png.png");
                    scrollImage++;
                    break;
                case 2:
                    width = (int)(game.blockSize*2.84375);
                    height = (int)(game.blockSize*3.5);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-9.png.png");
                    scrollImage++;
                    break;
                case 3:
                    width = (int)(game.blockSize*3.25);
                    height = (int)(game.blockSize*4);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-8.png.png");
                    scrollImage++;
                    break;
                case 4:
                    width = (int)(game.blockSize*3.65625);
                    height = (int)(game.blockSize*4.5);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-7.png.png");
                    scrollImage++;
                    break;
                case 5:
                    width = (int)(game.blockSize*4.0625);
                    height = (int)(game.blockSize*5);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-6.png.png");
                    scrollImage++;
                    break;
                case 6:
                    width = (int)(game.blockSize*4.46875);
                    height = (int)(game.blockSize*5.5);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-5.png.png");
                    scrollImage++;
                    break;
                case 7:
                    width = (int)(game.blockSize*4.875);
                    height = game.blockSize*6;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-4.png.png");
                    scrollImage++;
                    break;
                case 8:
                    width = (int)(game.blockSize*5.28125);
                    height = (int)(game.blockSize*6.5);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-3.png.png");
                    scrollImage++;
                    break;
                case 9:
                    width = (int)(game.blockSize*5.6875);
                    height = game.blockSize*4;
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-2.png.png");
                    scrollImage++;
                    break;
                case 10:
                    width = (int)(game.blockSize*6.09375);
                    height = (int)(game.blockSize*7.5);
                    image = ImageLoader.loadImage("/Scroll/Scroll_Open-1.png.png");
                    scrollImage++;
                    break;
            }
        }
    }

    private void readMe() {
       if (x <= MouseHandler.clickX && x + width >= MouseHandler.clickX) {
       System.out.println("X");
       if (
        y - height /2 <= MouseHandler.clickY && y + height >= MouseHandler.clickY) {
           System.out.println("Y");
           ScrollWorld scrollWorld = new ScrollWorld(game);
           World.setWorld(scrollWorld);
           MouseHandler.resetClicks();
       }
       }
    }

}

