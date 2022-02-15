import java.lang.Math;
import javax.swing.*;
//sound
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * Juego tipo rompecabezas de ordenar los numeros 
 */
public class DiceTaken
{
    Dice dice;
    private byte num=10, min=10, pred=10;
    private byte n;
    private int x,y;
    public Dice matrix[][];
    private int AcoorX, AcoorY, BcoorX, BcoorY;
    /**
     * Constructor for objects of class DiceTaken
     * @param byte n
     */
    public DiceTaken(byte n){
        this.n = n;
        this.matrix = new Dice[n][n];
        for (int i=0;i<n;i++){
            for (int j = 0;j<n;j++){
                dice = new Dice(num);
                matrix[i][j] = dice;
                matrix[i][j].change();
            }
        }
        for (int i = 0; i<n; i++){
            for (int j=0;j<n;j++){
                matrix[i][j].moveTo(i*(280),j*(280));
            }
        }
        int randomi = (int)(Math.random()*n+0);
        byte i = (byte) randomi;
        int randomj = (int)(Math.random()*n+0);
        byte j = (byte) randomj;
        matrix[randomi][randomj].makeInvisible();
        matrix[randomi][randomj].change(pred);
        minimo();
        verificar();
    }
    
    /**
     * Lanza el dado en la posicion dada junto con el digito que se quiere
     * @param int x
     * @param int y
     * @param int z
     */
    public void rotar(int x,int y, byte z){
        this.matrix[x][y].change(z);
        verificar();
    }
    
    /**
     * Lanza el dado en la posicion dada
     * @param int x
     * @param int y
     */
    public void rotar(int x,int y){
        this.matrix[x][y].change();
        minimo();
        verificar();
    }
    
    /**
     * Calcula el minimo en el tablero de dados
     */
    private void minimo(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(matrix[j][i].get()<min){
                    min = matrix[j][i].get();
                }
            }
        }
    }

    /**
     * Cambia de posicion los dados
     * @param int AcoorX
     * @param int AcoorY
     * @param int BcoorX
     * @param int BcoorY
     */
    private void deslizar(int AcoorX,int AcoorY,int BcoorX,int BcoorY){
        if(matrix[BcoorX][BcoorY].isVisible() == false){
            num = matrix[AcoorX][AcoorY].get();
            matrix[AcoorX][AcoorY].makeInvisible();
            matrix[AcoorX][AcoorY].change(pred);
            matrix[BcoorX][BcoorY].change(num);
            matrix[BcoorX][BcoorY].makeVisible();
            verificar();
        }
    }
    
    /**
     * Cambia de posicion del dado con el dado de la derecha
     */
    public void deslizarDerecha(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if((j+1)%(n)!=0){
                    deslizar(j, i, j+1, i);
                }
            }
        }
    }
    
    /**
     * Cambia de posicion del dado con el dado de la izquierda
     */
    public void deslizarIzquierda(){
        for(int i=0; i<n; i++){
            for(int j=(n-1); j>=0; j--){
                if(j!=0){
                    deslizar(j, i, j-1, i);
                }
            }
        }
    }
    
    /**
     * Cambia de posicion del dado con el dado de abajo
     */
    public void deslizarAbajo(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if((i+1)%n!=0){
                    deslizar(j, i, j, i+1);
                }
            }
        }
    }
    
    /**
     * Cambia de posicion del dado con el dado de arriba
     */
    public void deslizarArriba(){
        for(int i=(n-1); i>=0; i--){
            for(int j=0; j<n; j++){
                if(i!=0){
                    deslizar(j, i, j, i-1);
                }
            }
        }
    }
    
    /**
     * Verifica si los dados estan en orden en el tablero
     */
    private void verificar(){
        if(matrix[0][0].get() == min){
            matrix[0][0].changeColor("green");
        }
        else{
            matrix[0][0].changeColor("red");
        }
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if(matrix[j][i].get() <= matrix[(j+1)%n][(i+((j+1)/n))%n].get() && matrix[j][i].getColor()=="green" && matrix[(j+1)%n][(i+((j+1)/n))%n].isVisible()){
                    matrix[(j+1)%n][(i+((j+1)/n))%n].changeColor("green");
                }
                else if((j+1)%n!=0 || (i+((j+1)/n))%n!=0){
                    matrix[(j+1)%n][(i+((j+1)/n))%n].changeColor("red");
                }
            }
        }
        ganar();
    }
    
    /**
     * Verifica si la condicion de victoria se logro
     */
    private void ganar(){
        if(matrix[n-2][n-1].getColor() == "green"){
            parpadear();
            reproducirSonido("song.wav");
            JOptionPane.showMessageDialog(null,"Has ganado","Victoria", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Reproduce el sonido cuando se acaba el juego
     * @param String nombreSonido
     */
    private void reproducirSonido(String nombreSonido){
       try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
       }
    }
    
    /**
     * Parpadea el tablero cuando el juego se termina
     */
    private void parpadear(){
        for(int i=0; i<100000000; i++){
            for(int j=0; i<n; i++){
                for(int k=0; k<n; k++){
                    matrix[j][k].changeColor("red");
                    matrix[j][k].changeColor("blue");
                    matrix[j][k].changeColor("black");
                    matrix[j][k].changeColor("green");
                }
            }
        }
    }
}
