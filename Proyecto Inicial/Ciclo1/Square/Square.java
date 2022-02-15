import java.util.*;
import javax.swing.*;
import java.lang.Math;

/**
 * Represents a square <br>
 * (positon X, position Y, safetyDistance) <br>
 * 
 * @author Monroy Cardenas Miguel
 * @author Rodriguez Ropero Wilmer
 */
public class Square
{
    private final Rectangle square;
    private final int sD;
    
    private ArrayList<Dome> domes= new ArrayList();
    private ArrayList<Tourist> tourists= new ArrayList();
    
    private int length = 0;
    private int lengtht = 0;
    
    private Dome elem;
    
    /**
     * Creates a new square with the given parameters
     * @param dimensionX The width of the plane
     * @param dimensionY The height coordinate in the plane
     * @param safetyDistance The minimum distance a tourist can be from another
     */
    public Square(int dimensionX, int dimensionY, int safetyDistance)
    {
        square = new Rectangle();
        square.changeSize(dimensionX, dimensionY);
        sD = safetyDistance;
    }
    
    /**
     * Creates a new dome with the given parameters and adds it to a list
     * @param Color The color for the dome
     * @param x The x position for the dome
     * @param y The y position for the dome
     */
    public void addDome(String color, int x, int y){
        Dome dome = new Dome(color, x, y);
        domes.add(dome);
        length ++;
    }
    
    /**
     * Deletes a dome
     * @param dome The identifier (color) for the dome
     */
    public void delDome(String dome){
        for(Dome i: domes){
            if(i.getColor() == dome){
                elem = i;
            }
        }
        domes.remove(elem);
        elem.makeInvisible();
        length --;
    }
    
    /**
     * Creates a new tourist with the given parameters and adds it to a list
     */
    public void touristArrive(String color, int x, int y){
        for(Tourist i: tourists){
            if (sD < Math.sqrt((i.getPosition()[0] - x)^2 + (i.getPosition()[1]-y)^2)){
            Tourist tourist= new Tourist(color, x, y);
            tourists.add(tourist);
            lengtht ++;
            }
        }
    }
    
    /**
     * Moves a tourist from one position to another
     * @param color The identifier of the tourist
     * @param x The x position for the tourist
     * @param y The y position for the tourist
     */
    public void touristMove(String color, int x, int y){
        for(Tourist i: tourists){
            if (sD < Math.sqrt((i.getPosition()[0] - x)^2 + (i.getPosition()[1]-y)^2)){
                for(Tourist j: tourists){
                    if(i.getColor() == color){
                        j.touristMove(x, y);
                        }
                    }
            }
        }
    }
    
    /**
     * Takes a photo of the domes in the vision range of the toursit
     * @param tourist The identifier (color) of the tourist
     * return photo A string list of the domes in the vision range
     */
    public String[] touristTakePhoto(String tourist){
        String[] photo = new String[length];
        int n = 0;
        for(Tourist i: tourists){
            if(i.getColor() == tourist){
                i.resetLines();
                for(Dome j: domes){
                    if(i.view(j.getPosition())){
                        photo[n] = j.getColor();
                        n++;
                    }
                }
            }
        }
        return photo;
    }
    
    /**
     * Takes a photo of the domes in the vision range of the toursit
     * @param tourist The identifier (color) of the tourist
     * @param viewingAngle The angle at wich the tourist will take the photo
     * return photo A string list of the domes in the vision range
     */
    public String[] touristTakePhoto(String tourist,int viewingAngle){
        String[] photo = new String[length];
        int n = 0;
        for(Tourist i: tourists){
            if(i.getColor() == tourist){
                i.resetLines();
                i.rotate(viewingAngle);
                for(Dome j: domes){
                    if(i.view(j.getPosition())){
                        photo[n] = j.getColor();
                        n++;
                    }
                }
            }
        }
        return photo;
    }

    /**
     * Gives a string list of the domes in the square
     * return sDomes A string list of domes
     */
    public String[] domes(){
        int n = 0;
        String[] sDomes = new String[length];
        for(Dome i: domes){
            sDomes[n] = i.getColor();
            n++;
        }
        return sDomes;
    }
    
    /**
     * Gives a string list of the tourists in the square
     * return sDomes A string list of tourists
     */
    public String[] tourists(){
        int n = 0;
        String[] storuists = new String[lengtht];
        for(Tourist i: tourists){
            storuists[n] = i.getColor();
            n++;
        }
        return storuists;
    }
    
    /**
     * Makes the square visible
     */
    public void makeVisible(){
        square.makeVisible();
        for(Dome i: domes){            
            i.makeVisible();        
        }
        for(Tourist i: tourists){
            i.makeVisible();    
        }
    }
    
    /**
     * Makes the square Invisible
     */
    public void makeInvisible(){
        for(Dome i: domes){            
            i.makeInvisible();        
        }
        for(Tourist i: tourists){
            i.makeInvisible();    
        }
        square.makeInvisible();
    }
    
    /**
     * Finish the actual run of the program 
     */
    public void finish(){
        if (JOptionPane.showConfirmDialog(null, "Desea terminar el programa?", "Salir",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Checks if a method was completed correctly
     */
    private boolean ok(){
        return true;
    }
}
