/**
 * A representation of a dome <br>
 * Color, positon X, position Y) <br>
 * 
 * @author Monroy Cardenas Miguel
 * @author Rodriguez Ropero Wilmer
 */
public class Dome
{
    
    private String color;
    private int posX, posY;
    private Triangle dome;
    
    /**
     * Creates a new dome with the given parameters
     * @param Color The color that defines the dome
     * @param x The x coordinate in the plane
     * @param y The y coordinate in the plane
     */
    public Dome(String color, int x, int y)
    {
        dome = new Triangle();
        this.color = color;
        dome.changeColor(color);
        dome.changeSize(40,40);
        dome.changePosition(x, y);
        posX = x;
        posY = y;
    }

    /**
     * Changes The color of a dome
     * @param Color The new color for the dome
     */
    public void changeColor(String color)
    {
        dome.changeColor(color);
        this.color = color;
    }
    
    /**
     * Gives the actual position of the dome
     * @return pos The list of the coordinates for the dome
     */
    public int[] getPosition(){
        int[] pos = new int[2];
        pos[0] = posX;
        pos[1] = posY;
        return pos;
    }
    
    /**
     * Gives the color of the dome
     * @return color The dome color
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Makes the dome visible
     */
    public void makeVisible(){
        dome.makeVisible();
    }
    
    /**
     * Makes the dome invisibleR
     */
    public void makeInvisible(){
        dome.makeInvisible();
    }
}
