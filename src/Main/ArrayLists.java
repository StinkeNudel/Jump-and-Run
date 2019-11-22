package Main;

import java.util.ArrayList;

public class ArrayLists {
    public static ArrayList solidBlocks; //list of the blocks
    public static ArrayList player; // list of the player
    public static ArrayList enemys; //list of the enemys



    public ArrayLists() {
        solidBlocks = new ArrayList();
        player = new ArrayList();
        enemys = new ArrayList();

    }

    public static ArrayList getSolidBlocks() {
        return solidBlocks;
    }

    public static ArrayList getPlayer() {
        return player;
    }

    public static ArrayList getEnemys() {return enemys;}
}
