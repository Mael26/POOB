import java.util.*;
/**
 * Represents a tourist <br>
 * (Color, positon X, position Y) <br>
 * 
 * @author Monroy Cardenas Miguel
 * @author Rodriguez Ropero Wilmer
 */
public class Tourist
{
    
    private String color;
    private int posX, posY, angle, x1 = 40, y1 = 0; 
    private double dx1, dy1;
    private Circle tourist;
    private Circle view; 
    private Line conect;
    
    private ArrayList<Line> lines = new ArrayList();
    /**
     * Creates a new tourist with the given parameters
     * @param Color The color that defines the tourist
     * @param x The x coordinate in the plane
     * @param y The y coordinate in the plane
     */
    public Tourist(String color, int x, int y)
    {
        tourist = new Circle(); 
        this.color = color;
        tourist.changeColor(color);
        tourist.changePosition(x, y);
        tourist.changeSize(50);
        posX = x;
        posY = y;
        
        view = new Circle();
        view.changeSize(10);
        view.changeColor("black");
        view.changePosition(x+22+40,y+22);
    }

    /**
     * Changes the tourist position
     * @param @param x The x coordinate in the plane
     * @param y The y coordinate in the plane
     */
    public void touristMove(int x, int y){
        posX = x;
        posY = y;
        tourist.changePosition(x, y);
        view.changePosition(x+x1+22, y+y1+22);
    }
    
    /**
     * Changes the vision angle of the tourist
     * @param angle The angle of vision for the tourist
     */
    public void rotate(int angle){
        this.angle = angle;
        double dangle = angle;
        dangle = Math.toRadians(dangle);
        dx1 = (40*(Math.cos(dangle)));
        dy1 = (40*(Math.sin(dangle)));
        x1 = (int) dx1;
        y1 = (int) dy1;
        
        view.changePosition((posX+x1+20),(posY+y1+20));
    }
    
    /**
     * Draw lines for the domes that the tourist can see
     * @param pos A list of coordinates for the dome
     * @return boolean To decide if the dome can be seen 
     */
    public boolean view(int[] pos){
        int[] vector1 = new int[2], vector2 = new int[2];
        int dotProduct;
        double angle, normV1, normV2;
        vector1[0]=pos[0] - posX+25;
        vector1[1]=pos[1] - posY+25;
        vector2[0]=x1+20;
        vector2[1]=y1+20;
        dotProduct = vector1[0]*vector2[0] + vector1[1]*vector2[1];
        normV1= Math.sqrt(vector1[0]*vector1[0] + vector1[1]*vector1[1]);
        normV2= Math.sqrt(vector2[0]*vector2[0] + vector2[1]*vector2[1]);
        angle = Math.acos(dotProduct/(normV1*normV2));
        angle = Math.toDegrees(angle);
        if(angle <= 90){
            Line conect = new Line(posX+25, posY+25, pos[0], pos[1]+20);
            lines.add(conect);
            return true;
        }
        return false;
    }
    
    /**
     * Gives the actual position of the tourist
     * @return pos The list of the coordinates for the tourist
     */
    public int[] getPosition(){
        int[] pos = new int[2];
        pos[0] = posX;
        pos[1] = posY;
        return pos;
    }
    
    /**
     * Gives the color of the tourist
     * @return color The tourist color
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Deletes the old lines to avoid mistakes 
     */
    public void resetLines(){
        for(Line i: lines){
            i.makeInvisible();
        }
    }
    
    /**
     * Makes the tourist visible
     */
    public void makeVisible(){
        tourist.makeVisible();
        //view.makeVisible();
    }
    
    /**
     * Makes the tourist invisible
     */
    public void makeInvisible(){
        tourist.makeInvisible();
        //view.makeinvisible();
    }
}
