package shapes;

import java.awt.*;
import java.awt.geom.*;
/**
 * A line that can be manipulated and draws itself on a canvas.
 * 
 * @author Monroy Cardenas Miguel
 * @author Rodriguez Ropero Wilmer
 */
public class Line
{
    private double x1, x2, y1, y2;
    String color;
    private boolean isVisible;
    
    /**
     * Creates a new tourist with the given parameters
     * @param x1 The x coordinate for the first point
     * @param x2 The y coordinate for the first point
     * @param y1 The x coordinate for the second point
     * @param x2 The y coordinate for the second point
     */
    public Line(double x1,double x2,double y1,double y2)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        color = "blue";
        draw();
        isVisible = true;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Draw the line with current specifications on screen.
     */
    private void draw(){
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, color, new Line2D.Double(x1, x2, y1, y2));
        canvas.wait(10);
    }
    
    /**
     * Erase the line on screen.
     */
    private void erase(){
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(this);
    }
}
