package square;

import java.util.*;
import java.lang.AutoCloseable;
/**
 * Write a description of class TakeFixed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TakeFixed extends Tourist{

    /**
     * Constructor for objects of class TakeFixed
     */
    public TakeFixed(String color, int x, int y) {
        super(color, x, y);
    }

    @ Override
    public ArrayList<String> viewDomes(ArrayList<Dome> domes) {
        HashMap<String, Double> angles = new HashMap<>();
        ArrayList<String> viewDomes = new ArrayList<>();
        for(Dome dome : domes) {
            if(dome instanceof Fixed) {
                int[] pos = dome.getPosition();
                angles.put(dome.getColor(), viewAngle(pos)); //Calcular el angulo del domo respecto al turista
            } else if (dome instanceof Shy ) {
                dome.ocultarse(posX, posY, angle);
            }
        }
        //Organizar los domos de izquierda a derecha
        for(int i = 0; i < angles.size(); i ++) {
            Double min = 181.0; String dome = null;
            for(String key : angles.keySet()) {
                if(angles.get(key) >= 0 && angles.get(key) <= 180 && angles.get(key) < min) {
                    min = angles.get(key);
                    dome = key;
                }
            }
            if(dome != null) {
                viewDomes.add(dome);
                angles.put(dome, 181.0);
            }
        }
        return viewDomes;
    }
}
