/**
 * Dado formado a partir de objetos de las clases Circle y Rectangle
 * Se puede modificar el valor que muestra asi como su posicion y color
 */

import java.lang.Math;
import java.util.*;

public class Dice
{
    private Rectangle perimeter;
    private Circle c1, c2, c3, c4, c5, c6, c7;
    private ArrayList<Circle> array = new ArrayList<>();
    private byte x;
    private boolean isVisible;
    private String color;
    /**
     * Constructor for objects of class Dice
     * @param byte digit
     */
    public Dice(byte digit)
    {
        x = digit;
        perimeter = new Rectangle();
        c1 = new Circle();
        c2 = new Circle();
        c3 = new Circle();
        c4 = new Circle();
        c5 = new Circle();
        c6 = new Circle();
        c7 = new Circle();
        
        array.add(c1);
        array.add(c2);
        array.add(c3);
        array.add(c4);
        array.add(c5);
        array.add(c6);
        array.add(c7);
        
        perimeter.changeSize(250, 250);
        perimeter.changeColor("black");
        
        c1.moveHorizontal(100);
        c1.moveVertical(30);
        
        c2.moveHorizontal(225);
        c2.moveVertical(30);
        
        c3.moveHorizontal(100);
        c3.moveVertical(110);
        
        c4.moveHorizontal(225);
        c4.moveVertical(110);
        
        c5.moveHorizontal(100);
        c5.moveVertical(185);
        
        c6.moveHorizontal(225);
        c6.moveVertical(185);
        
        c7.moveHorizontal(163);
        c7.moveVertical(110);
        
        isVisible = true;
    
        set();
    }
    
    /**
     * Muestra el valor almacenado por el dado en el canvas
     */
    private void set(){
        if(x == 1){
            for(Circle i : array){
                i.makeInvisible();
            }
            c7.makeVisible();
        }
        if(x == 2){
            for(Circle i : array){
                i.makeInvisible();
            }
            c1.makeVisible();
            c6.makeVisible();
        }
        if(x == 3){
            for(Circle i : array){
                i.makeInvisible();
            }
            c2.makeVisible();
            c5.makeVisible();
            c7.makeVisible();
        }
        if(x == 4){
            for(Circle i : array){
                i.makeVisible();
            }
            c3.makeInvisible();
            c4.makeInvisible();
            c7.makeInvisible();
        }
        if(x == 5){
            for(Circle i : array){
                i.makeVisible();
            }
            c3.makeInvisible();
            c4.makeInvisible();
        }
        if(x == 6){
            for(Circle i : array){
                i.makeVisible();
            }
            c7.makeInvisible();
        }
    }
    
    /**
     * Permite saber cual es el valor del dado en el momento
     * @returns Int x
     */
    public byte get()
    {
        return x;
    }
    
    /**
     * Cambia el valor del dado por el siguiente, si llega a 6 se devuelve a 1
     */
    public void next()
    {
        if(x < 6){
            x ++;
        }
        else{
            x = 1;
        }
        set();
    }
    
    /**
     * Cambia el valor almacenado a dado por uno ingresado por el usuario
     * @param byte digit
     */
    public void change(byte digit){
        x = digit;
        set();
    }
    
    /**
     * Cambia el valor almacenado por el dado por uno aleatorio
     */
    public void change(){
        int random = (int)(Math.random()*6+1);
        byte n = (byte) random;
        x = n;
        set();
    }
    
    /**
     * Mueve el dado a las coordenadas dadas por el usuario
     * @param Int x
     * @param Int y
     */
    public void moveTo(int x, int y){
        perimeter.moveHorizontal(x);
        perimeter.moveVertical(y);
        
        for(Circle i : array){
            i.moveHorizontal(x);
            i.moveVertical(y);
        }
    }
    
    /**
     * Cambia el color de los circulos en el dado por uno ingresado por el usuario
     * @param String color
     */
    public void changeColor(String color){
        for(Circle i: array){
            i.changeColor(color);
            this.color = "green"; 
        }
    }
    
    public void makeVisible(){
        perimeter.makeVisible();
        this.isVisible = true;
        for(Circle i: array){
            i.makeVisible();
        }
    }
    
    public void makeInvisible(){
        perimeter.makeInvisible();
        this.isVisible = false;
        for(Circle i: array){
            i.makeInvisible();
        }
    }
    
    public boolean isVisible(){
        return this.isVisible;
    }
    
    
    
    public String getColor(){
        return color;
    }
}
