package square;

import shapes.*;
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
public class Square {
    private final Rectangle square;
    private final int sDistance;

    private ArrayList<Dome> domes= new ArrayList();
    private ArrayList<Tourist> tourists= new ArrayList();

    private String[] requestedPhoto;
    private Rectangle areaPhoto; 

    public static boolean isVisible = false;
    private boolean ok;

    /**
     * Creates a new square with the given parameters, the dimensions should be between 200 and 1500 for X and Y. The dimension should be lower than the dimension.
     * @param dimensionX The width of the plane
     * @param dimensionY The height coordinate in the plane
     * @param safetyDistance The minimum distance a tourist can be from another
     */
    public Square(int dimensionX, int dimensionY, int safetyDistance) {
        if (200 <= dimensionX && dimensionX <= 1500 && 200 <= dimensionY && dimensionY <= 1500) {
            square = new Rectangle();
            areaPhoto = new Rectangle();
            sDistance = safetyDistance;
            areaPhoto.changeColor("cyan");
            square.changeSize(dimensionX, dimensionY);
            this.ok = true;
        } else {
            square = null;
            sDistance = 0;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "La plaza no cumple con el tamaño adecuado");
            }
            this.ok = false;
        }
    }

    /**
     * Creates a new square with the given parameters of width, height as well as the coordinates for new domes and the list of the desired view,
     * the dimensions should be between 200 and 1500 for X and Y
     * @param int[] dimensions for the width and height of the square
     * @param int[][] for the coordinates of the domes arranged in pairs 
     * @param int[] for the desired order of the domes in the photo
     */
    public Square(int[] dimensions, int[][] positions, int[] desiredView) {
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<String> requestedPhoto = new ArrayList<>();
        String[] color = {"green", "red", "black", "blue","yellow", "magenta", "white", "dark", "gray", "orange", "pink"};
        String[] requestedPhotoA = new String[desiredView.length];
        if (200 <= dimensions[0] && dimensions[0] <= 1500 && 200 <= dimensions[1] && dimensions[1] <= 1500) {
            sDistance = 0;
            square = new Rectangle();
            areaPhoto = new Rectangle();
            square.changeSize(dimensions[0], dimensions[1]);
            areaPhoto.changeColor("cyan");
            for(int i = 0; i < positions.length; i++) {
                this.addDome(color[i], positions[i][0], positions[i][1]);
                colors.add(color[i]);
            }
            for(int view : desiredView) {
                requestedPhoto.add(colors.get(view - 1));
            }
            requestedPhotoA = requestedPhoto.toArray(requestedPhotoA);
            defineRequestedPhoto(requestedPhoto.toArray(requestedPhotoA));
            ok = true;
        } else {
            square = null;
            sDistance = 0;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "La plaza no cumple con el tamaño adecuado");
            }
            ok = false;
        }
    }

    /**
     * Defines a list of domes in the desires order for the photo
     * @param String domes - The colors of the domes in the desired order
     */
    public void defineRequestedPhoto(String[] domesPhoto) {
        Map<String, Dome> domesMap = new HashMap<>();
        int[] min = {1000, 1000}, max = {0, 0}, pos = new int[2];
        boolean flag = true;
        for(Dome dome : domes) {
            domesMap.put(dome.getColor(), dome);
        }
        for(String domePhoto : domesPhoto) {
            if(!domesMap.keySet().contains(domePhoto)) {
                flag = false;
            } else {
                pos = domesMap.get(domePhoto).getPosition();
                if(min[0] > pos[0]) { min[0] = pos[0];}
                if(min[1] > pos[1]) { min[1] = pos[1];}
                if(max[0] < pos[0]) { max[0] = pos[0];}
                if(max[1] < pos[1]) { max[1] = pos[1];}
            }
        }
        if(flag) {
            areaPhoto.makeInvisible();
            areaPhoto.changeSize(max[1] - min[1] + 100, max[0] - min[0] + 100);
            areaPhoto.changePosition(min[0] - 50, min[1] - 25);
            requestedPhoto = domesPhoto;
            ok = true;
        } else if (isVisible) {
            JOptionPane.showMessageDialog(null, "Un domo no esta definido");
            ok = false;
        }
    }

    /**
     * Creates a new dome with the given parameters and adds it to a list,
     * the aviable colors are red, black, blue, yellow, green, magenta, white, cyan, dark (dark gray),
     * light (light gray), gray, orange, pink.
     * The parameters for the dome position must be inside of the square dimension.
     * @param Color The color for the dome
     * @param x The x position for the dome in the plane
     * @param y The y position for the dome in the plane
     */
    public void addDome(String color, int x, int y) {
        boolean flag =true, ok = true;
        for(Dome dome : domes) {
            if(dome.getColor().equals(color) && flag) {
                flag = false; ok = false;
                if(isVisible){
                    JOptionPane.showMessageDialog(null, "El domo ya existe");
                }
            }
        }
        if(flag) {
            Dome dome = new Dome(color, x, y);
            domes.add(dome);
            if (isVisible){
                dome.makeVisible();
            }
        }
    }

    /**
     * Creates a new dome with the given parameters and adds it to a list,
     * the aviable types are normal, shy, fixed,
     * the aviable colors are red, black, blue, yellow, green, magenta, white, cyan, dark (dark gray),
     * light (light gray), gray, orange, pink.
     * The parameters for the dome position must be inside of the square dimension.
     * @param type the type of dome 
     * @param color The color for the dome
     * @param x The x position for the dome in the plane
     * @param y The y position for the dome in the plane
     */
    public void addDome(String type,String color, int x, int y){
        boolean flag =true, ok = true;
        for(Dome dome : domes) {
            if(dome.getColor().equals(color) && flag) {
                flag = false; ok = false;
                if(isVisible){
                    JOptionPane.showMessageDialog(null, "El domo ya existe");
                }
            }
        }
        if(flag) {
            switch(type){
                case "normal":
                    Dome dome = new Dome(color, x, y);
                    domes.add(dome);
                    if (isVisible){
                        dome.makeVisible();
                    }
                    break;
                case"fixed":
                    Fixed fixed = new Fixed(color, x, y);
                    domes.add(fixed);
                    if (isVisible){
                        fixed.makeVisible();
                    }
                    break;
                case"shy":
                    Shy shy = new Shy(color, x, y);
                    domes.add(shy);
                    if (isVisible){
                        shy.makeVisible();
                    }
                    break;
            }
        }
    }
    
    /**
     * Deletes a dome only if its not an instance of a Fixed dome
     * @param dome The identifier (color) for the dome
     */
    public void delDome(String dome) {
        Dome elem = null;
        for(Dome dom: domes) {
            if(dom.getColor() == dome) {
                elem = dom;
            }
        }
        if(elem != null &&  !(elem instanceof Fixed)) {
            domes.remove(elem);
            elem.makeInvisible();
            ok = true;
        } else if (isVisible && !(elem instanceof Fixed)) {
            JOptionPane.showMessageDialog(null, "El domo no existe");
            ok = false;
        }
    }

    /**
     * Creates a new tourist with the given parameters and adds it to a list, if the new tourist is too close to another one it shows a message and the tourist is not created
     * the aviable colors are red, black, blue, yellow, green, magenta, white, cyan, dark (dark gray),
     * light (light gray), gray, orange, pink.
     * The parameters for the tourist position must be inside of the square dimension.
     * @param String Color for the identifier color of the tourist
     * @param int x for the x coordinate in the plane
     * @param int y for the y coordinate in the plane
     */
    public void touristArrive(String color, int x, int y) {
        boolean flag = true, ok = true;
        Tourist nTourist = new Tourist(color, x, y);
        for(Tourist tourist: tourists) {
            if (nTourist.distance(tourist.getPosition(), sDistance) && flag) {
                flag = false; ok = false;
                if(isVisible){
                    JOptionPane.showMessageDialog(null, "Los turistas estan muy cerca");
                }
            }
        }
        if(flag) {
            tourists.add(nTourist);
            if(isVisible) {
                nTourist.makeVisible();
            }
        }
        //System.out.println(ok());
    }

    /**
     * Creates a new tourist with the given parameters and adds it to a list, if the new tourist is too close to another one it shows a message and the tourist is not created
     * the aviable types are normal, prudent, perfectionist, takeFixed
     * the aviable colors are red, black, blue, yellow, green, magenta, white, cyan, dark (dark gray),
     * light (light gray), gray, orange, pink.
     * The parameters for the tourist position must be inside of the square dimension.
     * @param String type the type of tourist
     * @param String Color for the identifier color of the tourist
     * @param int x for the x coordinate in the plane
     * @param int y for the y coordinate in the plane
     */
    public void touristArrive(String type,String color, int x, int y) {
        boolean flag = true, ok = true;
        Tourist nTourist = null;
        switch (type) {
            case "normal": nTourist = new Tourist(color, x, y);
            break;
            case "prudent": nTourist = new Prudent(color, x, y);
            break;
            case "perfectionist": nTourist = new Perfectionist(color, x, y);
            break;
            case "takeFixed": nTourist = new TakeFixed(color, x, y);
            break;
        }
        for(Tourist tourist: tourists) {
            if (nTourist.distance(tourist.getPosition(), sDistance) && flag) {
                flag = false; ok = false;
                if(isVisible){
                    JOptionPane.showMessageDialog(null, "Los turistas estan muy cerca");
                }
            }
        }
        if(flag) {
            tourists.add(nTourist);
            if(isVisible) {
                nTourist.makeVisible();
            }
        }
        //System.out.println(ok());
    }

    /**
     * Moves a tourist from one position to another, if the new position is too close to an existing tourist its shown a message and the tourist doesn't move
     * The parameters for the tourist position must be inside of the square dimension.
     * @param color The identifier of the tourist
     * @param x The x position for the tourist in the plane
     * @param y The y position for the tourist in the plane
     * @param angle the angle for tourist in the plane
     */
    public void touristMove(String color, int x, int y, int angle) {
        boolean flag = true, ok = false;
        int[] pos = {x, y};
        for(Tourist tourist: tourists) {
            if (tourist.distance(pos, sDistance) && flag) {
                flag = false; ok = false;
                if(isVisible){
                    JOptionPane.showMessageDialog(null, "Los turistas estan muy cerca");
                }
            }
        }
        if(flag) {
            for(Tourist tourist: tourists){
                if(tourist.getColor().equals(color)) {
                    tourist.rotate(angle);
                    tourist.touristMove(x, y);
                }
            }
        }
    }

    /**
     * Takes a photo of the domes in the vision range of the toursit
     * @param tourist The identifier (color) of the tourist
     * @return photoArray A string list of the domes in the vision range
     */
    public String[] touristTakePhoto(String tourist) {
        ok = false;
        ArrayList<String> photo = new ArrayList<>();
        for(Tourist touri : tourists) { //Busca el turista
            if(touri.getColor() == tourist) {
                photo = touri.viewDomes(domes);
                String[] photoArray = new String[photo.size()];
                photoArray = photo.toArray(photoArray);
                if (touri instanceof Perfectionist && !Arrays.equals(requestedPhoto, photoArray) || photo.size() == 0) {
                    return null;
                } else {
                    ok = true;
                    return photoArray;
                }
            }
        }
        if(isVisible) {JOptionPane.showMessageDialog(null, "No hay domos visibles");}
        return null;
    }

    /**
     * Takes a photo of the domes in the vision range of the toursit
     * The angle must be between 0 and 359
     * @param tourist The identifier (color) of the tourist
     * @param viewingAngle The angle at wich the tourist will take the photo
     * @return photo A string list of the domes in the vision range
     */
    public String[] touristTakePhoto(String tourist,int viewingAngle) {
        Tourist touri;
        String[] photo = null;
        for(Tourist tour : tourists) {
            if(tour.getColor().equals(tourist)) {
                tour.rotate(viewingAngle);
                photo = touristTakePhoto(tourist);
            }
        }
        return photo;
    }

    /**
     * Gives the String for the tourist that is in position to take the requested photo
     * @return String The identifier color if the tourist thet can take the photo
     */
    public String[] whoRequestedPhoto() {
        ArrayList<String> tourists = new ArrayList<>();
        boolean ok = true;
        for(Tourist tourist : this.tourists) {
            String color = tourist.getColor();
            String[] photo = touristTakePhoto(color);
            if(Arrays.equals(requestedPhoto, photo)) {
                tourists.add(color);
            }
        }
        String[] touristsArray = new String[tourists.size()];
        touristsArray = tourists.toArray(touristsArray);
        //System.out.println(ok());
        return touristsArray;
    }

    /**
     * If a tourist that can take the requested photo exists, then it takes the photo
     */
    public void takeRequestedPhoto(){
        if(requestedPhoto.length == 0 && isVisible){
            JOptionPane.showMessageDialog(null, "No hay domos visibles");
        } else {
            String[] possible = whoRequestedPhoto();
            for (String poss : possible){
                touristTakePhoto(poss);
            }
            makeVisible();
        }
    }

    /**
     * Gives a string list of the domes in the square
     * @return sDomes A string list of domes
     */
    public String[] domes() {
        int n = 0;
        boolean ok = true;
        String[] sDomes = new String[domes.size()];
        for(Dome i: domes) {
            sDomes[n] = i.getColor();
            n++;
        }
        //System.out.println(ok());
        return sDomes;
    }

    /**
     * Gives a string list of the tourists in the square
     * @return sDomes A string list of tourists
     */
    public String[] tourists() {
        int n = 0;
        boolean ok = true;
        String[] sToruists = new String[tourists.size()];
        for(Tourist i: tourists) {
            sToruists[n] = i.getColor();
            n++;
        }
        //System.out.println(ok());
        return sToruists;
    }

    /**
     * Given a string of a tourist it returns a list of integers
     * @param String tourist the identifier color for the tourist
     * @return result: A list of integers with the position vision angle 
     */
    public int[] tourist(String tourist) {
        int[] result = new int[2];
        boolean flag = false, ok = false;
        for(Tourist tour : tourists) {
            if(tour.getColor() == tourist) {
                flag = true; ok = true;
                result[0] = tour.getPosition()[0];
                result[1] = tour.getPosition()[1];
            }
        }
        if (!flag){
            if(isVisible){
                JOptionPane.showMessageDialog(null, "El turista no existe");
            }
        }
        //System.out.println(result);
        return result;
    }

    /**
     * Given a string of a dome it returns a list of integers
     * @param String dome the identifier color for the dome
     * @return result: A list of integers with the position
     */
    public int[] dome(String dome) {
        int [] result = new int[2];
        boolean flag = false, ok = false;
        for(Dome dom : domes) {
            if(dom.getColor() == dome) {
                flag = true; ok = true;
                result = dom.getPosition();
            }
        }
        if (!flag){
            ok = false;
            if(isVisible){
                JOptionPane.showMessageDialog(null, "El domo no existe");
            }
        }
        //System.out.println(ok());
        return result;
    }

    /**
     * Makes the square visible
     */
    public void makeVisible() {
        ok = true;
        square.makeVisible();
        areaPhoto.makeVisible();
        for(Dome i: domes) {            
            i.makeVisible();        
        }
        for(Tourist i: tourists) {
            i.makeVisible();    
        }
        isVisible = true;
        //System.out.println(ok());
    }

    /**
     * Makes the square Invisible
     */
    public void makeInvisible() {
        ok = true;
        for(Dome i: domes) {            
            i.makeInvisible();        
        }
        for(Tourist i: tourists) {
            i.makeInvisible();    
        }
        areaPhoto.makeInvisible();
        square.makeInvisible();
        isVisible = false;
        //System.out.println(ok());
    }

    /**
     * Finish the actual run of the program 
     */
    public void finish() {
        if (JOptionPane.showConfirmDialog(null, "Desea terminar el programa?", "Salir",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Checks if a method was completed correctly
     * @return boolean ok
     */
    public boolean ok() {
        return ok;
    }

    public static void main(String args[]){
		Square plaza = new Square(1500,1500,10);
        plaza.touristArrive("blue", 20, 100);
        plaza.touristArrive("red", 500, 500);
        plaza.addDome("orange", 100, 500);
        plaza.addDome("pink", 600, 300);
        plaza.makeVisible();
	}
}

