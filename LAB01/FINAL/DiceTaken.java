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
    private byte num =1;
    private byte n;
    private int x,y;
    public Dice matrix[][];
    private byte AcoorX, AcoorY, BcoorX, BcoorY;
    private byte min = 100;
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
                if (matrix[i][j].get()<min && (i != n && j != n)){
                    min = matrix[i][j].get();                    
                }
            }
        }
        for (int i = 0; i<n; i++){
            for (int j=0;j<n;j++){
                matrix[i][j].moveTo(i*(280),j*(280));
            }
        }
        matrix[n-1][n-1].makeInvisible();
        verificar();
    }
    
    public void rotar(int x,int y, byte z){
        this.matrix[x][y].change(z);
        verificar();
    }
    
    public void rotar(int x,int y){
        this.matrix[x][y].change();
        if (matrix[x][y].get()<min){
            min = matrix[x][y].get();
                }
        verificar();
    }
    
    public void deslizar(byte AcoorX,byte AcoorY,byte BcoorX,byte BcoorY){
        if ((this.matrix[AcoorX][AcoorY].isVisible() == false || this.matrix[BcoorX][BcoorY].isVisible() == false) && ((Math.abs(AcoorX-BcoorX)+Math.abs(AcoorY-BcoorY))== 1)){
            if(this.matrix[AcoorX][AcoorY].isVisible() == false){
                num = this.matrix[BcoorX][BcoorY].get();
                this.matrix[BcoorX][BcoorY].makeInvisible();
                
                this.matrix[AcoorX][AcoorY].makeVisible();
                this.matrix[AcoorX][AcoorY].change(num);
            }
            else{
                num = this.matrix[AcoorX][AcoorY].get();
                this.matrix[AcoorX][AcoorY].makeInvisible();
                
                this.matrix[BcoorX][BcoorY].makeVisible();
                this.matrix[BcoorX][BcoorY].change(num);
            }
        }
        verificar();
    }
    private void verificar(){
        if (this.matrix[0][0].get() == min){
                    this.matrix[0][0].changeColor("green");
                }
        for (int i=1;i<n;i++){
            if( (matrix[i][0].get() >= matrix[i-1][0].get()) && (matrix[i-1][0].getColor()=="green")){
                this.matrix[i][0].changeColor("green");
            }
        }
        for (int i=1;i<n;i++){
            if( (matrix[0][i].get() >= matrix[n-1][i-1].get()) && (matrix[n-1][i-1].getColor()=="green")){
                this.matrix[0][i].changeColor("green");
                System.out.println(matrix[0][i].get());
            }
        }
        for (int i=1;i<n;i++){
            for (int j = 1;j<n;j++){
                if(( matrix[i][j].get() >= matrix[i-1][j].get()) && (matrix[i-1][j].getColor()=="green")){
                this.matrix[i][j].changeColor("green");
            }
            }
        }
        ganar();
    }
    
    private void ganar(){
        if(matrix[n-2][n-1].getColor() == "green"){
            parpadear();
            reproducirSonido("song.wav");
            JOptionPane.showMessageDialog(null,"Has ganado","Victoria", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void reproducirSonido(String nombreSonido){
       try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
       }
    }
    
    public void parpadear(){
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
