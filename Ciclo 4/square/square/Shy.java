package square;


/**
 * Write a description of class Shy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shy extends Dome {
    
    /**
     * Constructor for objects of class Shy
     */
    public Shy(String color, int x, int y){
        super(color, x, y);
    }
    
    @Override
    public void ocultarse(int x, int y, int angle){
        double dx1, dy1;

        double dangle = angle;
        dangle = Math.toRadians(dangle);

        dx1 = (40*(Math.cos(dangle)));
        dy1 = (40*(Math.sin(dangle)));
        int x1 = (int) dx1;
        int y1 = (int) dy1;

        this.changePosition((x - x1 + 25),(y - y1 + 15));
    }
    
    /**
     * Change the position of the figure
     */
    public void changePosition(int x, int y){
        posX = x;
        posY = y;
        dome.changePosition(posX, posY);
    }
}