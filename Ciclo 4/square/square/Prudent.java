package square;

import java.util.*;
import java.lang.AutoCloseable;

/**
 * Write a description of class Prudent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Prudent extends Tourist {

    /**
     * Constructor for objects of class Prudent
     */
    public Prudent(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean distance(int pos[], int sDistance) {
        int[] vector = new int[]{pos[0] - this.posX, pos[1] - this.posY};
        double distance = Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
        if (distance <= sDistance * 2) {
            return true;
        }
        return false;
    }
}
