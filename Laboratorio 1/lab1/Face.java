/**
 * Una cara formada a partir de otros objetos del paquete shapes
 * 
 */

public class Face
{
    Circle face;
    Circle rEye, lEye;
    Rectangle mouth;
    /**
     * Constructor for objects of class Face
     */
    public Face()
    {
        face = new Circle();
        mouth = new Rectangle();
        rEye = new Circle();
        lEye = new Circle();
        
        face.changeSize(200);
        face.changeColor("yellow");
        face.makeVisible();
        
        mouth.changeSize(10,100);
        mouth.changeColor("red");
        mouth.moveVertical(120);
        mouth.makeVisible();
        
        rEye.changeSize(20);
        rEye.moveVertical(50);
        rEye.moveHorizontal(50);
        rEye.makeVisible();
        
        lEye.changeSize(20);
        lEye.moveVertical(50);
        lEye.moveHorizontal(140);
        lEye.makeVisible();
        
    }
}
