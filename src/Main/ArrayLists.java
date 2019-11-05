package Main;

import java.util.ArrayList;

public class ArrayLists {
    public static ArrayList solidBlocks; //list of the blocks
    public static ArrayList player; // list of the player



    public ArrayLists() {
        solidBlocks = new ArrayList();
        player = new ArrayList();

    }

    public static ArrayList getSolidBlocks() {
        return solidBlocks;
    }

    public static ArrayList getPlayer() {
        return player;
    }
}
