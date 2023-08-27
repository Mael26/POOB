package square;

import shapes.*;
import java.util.*;
import java.lang.AutoCloseable;
/**
 * Represents a tourist <br>
 * (Color, positon X, position Y) <br>
 * 
 * @author Monroy Cardenas Miguel
 * @author Rodriguez Ropero Wilmer
 */
public class Tourist {
    protected String color;
    protected int posX, posY, angle, x1 = 15, y1 = 0;
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
    public Tourist(String color, int x, int y) {
        tourist = new Circle(); 
        this.color = color;
        tourist.changeColor(color);
        tourist.changePosition(x, y);
        tourist.changeSize(20);
        posX = x;
        posY = y;
        
        angle = 0;
        view = new Circle();
        view.changeSize(5);
        view.changeColor("black");
        view.changePosition(x + 25, y + 8);
    }

    /**
     * Changes the tourist position
     * @param @param x The x coordinate in the plane
     * @param y The y coordinate in the plane
     */
    public void touristMove(int x, int y) {
        posX = x;
        posY = y;
        tourist.changePosition(x, y);
        view.changePosition(x + x1 + 8, y + y1 + 8);
    }

    /**
     * Changes the vision angle of the tourist
     * @param angle The angle of vision for the tourist
     */
    public void rotate(int angle) {
        double dx1, dy1;

        this.angle = angle;
        double dangle = angle;
        dangle = Math.toRadians(dangle);

        dx1 = (15*(Math.cos(dangle)));
        dy1 = (15*(Math.sin(dangle)));
        x1 = (int) dx1;
        y1 = (int) dy1;

        view.changePosition((posX + x1 + 8),(posY + y1 + 8));
    }

    /**
     * Calculates the angle between a tourist and a dome
     * @param pos A list of coordinates for the dome
     * @return angleDome A list of angles
     */
    public double viewAngle(int[] pos) {
        double[] vectorDome = new double[]{pos[0] - posX, pos[1] - posY }, vectorVision = new double[]{x1 , y1};
        double domeAngle, normVd, normVv, dotProduct;
        //producto punto entre el vector que se forma con la posicion del domo, y con el vector del angulo de la vista del turista
        dotProduct = vectorDome[0]*vectorVision[0] + vectorDome[1]*vectorVision[1];
        //Norma de ambos vectores
        normVd= Math.sqrt(vectorDome[0]*vectorDome[0] + vectorDome[1]*vectorDome[1]);
        normVv= Math.sqrt(vectorVision[0]*vectorVision[0] + vectorVision[1]*vectorVision[1]);
        //Calcular el angulo con la formula de "angulo = productoPunto(a, b)/multiplicarNormas(a, b)" y se pasa a grados
        domeAngle = Math.toDegrees(Math.acos(dotProduct/(normVd*normVv)));
        if(domeAngle <= 90){
            // Calcular el angulo respecto al vector de la vista completa
            double angle = Math.toRadians(this.angle - 90), normVa;
            // Se crea el vector de la vista completa
            double [] vectorAngle = new double[]{Math.cos(angle), Math.sin(angle)};
            // Producto punto entre vector de domos y el de la vista
            dotProduct = vectorDome[0]*vectorAngle[0] + vectorDome[1]*vectorAngle[1];
            normVa= Math.sqrt(vectorAngle[0]*vectorAngle[ 0] + vectorAngle[1]*vectorAngle[1]);
            // Calcular el angulo con la misma formula de antes
            domeAngle = Math.toDegrees(Math.acos(dotProduct/(normVd*normVa)));
            return domeAngle;
        }
        return 181.0;
    }

    public ArrayList<String> viewDomes(ArrayList<Dome> domes) {
        HashMap<String, Double> angles = new HashMap<>();
        ArrayList<String> viewDomes = new ArrayList<>();
        for(Dome dome : domes) {
            if(dome instanceof Shy){
                dome.ocultarse(posX, posY, angle);
            }
            int[] pos = dome.getPosition();
            angles.put(dome.getColor(), viewAngle(pos)); //Calcular el angulo del domo respecto al turista
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

    /**
     * Calculates the distance between two objects
     * @param posX
     * @param posY
     * @return distance The distance in double
     */
    //falta documentar
    public boolean distance(int[] pos , int sDistance) {
        int[] vector = new int[]{pos[0] - this.posX, pos[1] - this.posY};
        double distance = Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
        if (distance <= sDistance) {
            return true;
        }
        return false;
    }

    /**
     * Gives the actual position of the tourist
     * @return pos The list of the coordinates for the tourist
     */
    public int[] getPosition() {
        int[] pos = new int[2];
        pos[0] = posX;
        pos[1] = posY;
        return pos;
    }

    /**
     * Gives the color of the tourist
     * @return color The tourist color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gives the viewing angle of the tourist
     * @return Angle of the tourist
     */
    public int getView() {
        return angle;
    }

    /**
     * Deletes the old lines to avoid mistakes 
     */
    public void resetLines() {
        for(Line i: lines){
            i.makeInvisible();
        }
    }

    /**
     * Makes the tourist visible
     */
    public void makeVisible(){
        tourist.makeVisible();
        view.makeVisible();
    }

    /**
     * Makes the tourist invisible
     */
    public void makeInvisible(){
        tourist.makeInvisible();
        view.makeInvisible();
    }
}
