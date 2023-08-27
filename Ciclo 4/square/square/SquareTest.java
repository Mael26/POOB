package square;

import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SquareTest.
 *
 * @Monroy
 * @Rodriguez
 * @version (a version number or a date)
 */
public class SquareTest
{
    Square square;
    @Before 
    public void Create(){
        square = new Square(1500,1500,10);
    }

    @Test 
    public void ShouldCreate1(){
        assertEquals(true, square.ok());
    }

    @Test 
    public void SouldCreate2(){
        int[] dimensions1 = {1500,1500};
        int[][] domes = {{20,20},{100,20},{30,200}};
        int[] desiredView = {3,1,2};
        square = new Square(dimensions1, domes, desiredView);
        assertEquals(true, square.ok());
    }

    @Test 
    public void ShouldNotCreate1(){
        square = new Square(100,15,0);
        assertEquals(false, square.ok());
    }

    @Test 
    public void ShouldNotCreate2(){
        int[] dimensions2 = {5000,5000};
        int[][] domes = {{20,20},{100,20},{30,200}};
        int[] desiredView = {3,1,2};
        square = new Square(dimensions2, domes, desiredView);
        assertEquals(false, square.ok());
    }

    @Test 
    public void ShouldAddDome(){
        //square = new Square(1500,1500,0);
        square.addDome("red", 20, 20);
        String[] shouldHave = {"red"};
        assertEquals(shouldHave, square.domes());
    }

    @Test 
    public void ShouldNotAddDome(){
        //square = new Square(1500,1500,0);
        square.addDome("red", 20, 20);
        square.addDome("blue", 100, 100);
        square.addDome("red", 200, 200);
        String[] shouldHave = {"red","blue"};
        assertEquals(shouldHave, square.domes());
    }

    @Test 
    public void ShouldDeleteDome(){
        //square = new Square(1500,1500,0);
        square.addDome("red", 20, 20);
        square.addDome("blue", 100, 100);
        square.addDome("orange", 200, 200);
        square.delDome("orange");
        String[] shouldHave = {"red","blue"};
        assertEquals(shouldHave, square.domes());
    }

    @Test 
    public void ShouldNotDeleteDome(){
        //square = new Square(1500,1500,0);
        square.addDome("red", 20, 20);
        square.addDome("blue", 100, 100);
        square.addDome("orange", 200, 200);
        square.delDome("green");
        String[] shouldHave = {"red","blue","orange"};
        assertEquals(shouldHave, square.domes());
    }

    @Test 
    public void ShouldDefinePhoto(){
        //square = new Square(1500,1500,0);
        square.addDome("red", 20, 20);
        square.addDome("blue", 100 , 100);
        String[] requestedPhoto = {"red","blue"} ;
        square.defineRequestedPhoto(requestedPhoto);
        assertEquals(true, square.ok());
    }

    @Test 
    public void ShouldNotDefinePhoto(){
        //square = new Square(1500,1500,0);
        square.addDome("red", 20, 20);
        String[] requestedPhoto = {"red","blue"} ;
        square.defineRequestedPhoto(requestedPhoto);
        assertEquals(true, square.ok());
    }

    @Test 
    public void SouldTouristArrive(){
        //square = new Square(1500,1500,0);
        square.touristArrive("red", 20, 20);
        String[] shouldHave = {"red"} ;
        assertEquals(shouldHave, square.tourists());
    }

    @Test 
    public void ShouldNotTouristArrive(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 20, 20);
        square.touristArrive("blue", 21, 21);
        String[] shouldHave = {"red"} ;
        assertEquals(shouldHave, square.tourists());
    }

    @Test 
    public void ShouldTouristMove(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 20, 20);
        square.touristMove("red", 30, 30, 0);
        assertEquals(true, square.ok());
    }

    @Test 
    public void ShouldTakePhoto(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 20, 20);
        square.addDome("red", 200, 20);
        square.addDome("blue", 100 , 100);
        square.touristTakePhoto("red");
        String[] shouldBe = {"red","blue"};
        assertEquals(shouldBe, square.touristTakePhoto("red"));
    }

    @Test 
    public void ShouldNotTakePhoto(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 20, 20);
        square.addDome("red", 200, 20);
        square.addDome("blue", 100 , 100);
        square.touristTakePhoto("green");
        String[] shouldBe = {"blue","red"};
        assertEquals(false, square.ok());
    }

    @Test 
    public void ShouldTakePhoto2(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 20, 20);
        square.addDome("red", 200, 20);
        square.addDome("blue", 100 , 100);
        square.touristTakePhoto("red", 358);
        String[] shouldBe = {"red","blue"};
        assertEquals(shouldBe, square.touristTakePhoto("red"));
    }

    @Test 
    public void ShouldNotTakePhoto2(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 20, 20);
        square.addDome("red", 200, 20);
        square.addDome("blue", 100 , 100);
        square.touristTakePhoto("red",180);
        String[] shouldBe = {"blue","red"};
        assertEquals(false, square.ok());
    }

    @Test 
    public void ShouldDomes(){
        //square = new Square(1500,1500,10);
        square.addDome("red", 200, 20);
        square.addDome("blue", 100 , 100);
        String[] shouldBe = {"red","blue"};
        assertEquals(shouldBe, square.domes());
    }

    @Test 
    public void ShouldNotDomes(){
        //square = new Square(1500,1500,10);
        square.addDome("red", 200, 20);
        square.addDome("blue", 100 , 100);
        square.delDome("red");
        String[] shouldNotBe = {"red","blue"};
        assertNotEquals(shouldNotBe, square.domes());
    }

    @Test 
    public void ShouldTourists(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 200, 20);
        square.touristArrive("blue", 100 , 100);
        String[] shouldBe = {"red","blue"};
        assertEquals(shouldBe, square.tourists());
    }

    @Test 
    public void ShouldNotTourists(){
        //square = new Square(1500,1500,10);
        square.touristArrive("red", 200, 20);
        square.touristArrive("blue", 100 , 100);
        square.touristArrive("green",101,101);
        String[] shouldNotBe = {"red","blue","green"};
        assertNotEquals(shouldNotBe, square.tourists());
    }

    @Test 
    public void ShouldDome(){
        //square = new Square(1500,1500,10);
        square.addDome("red", 200, 20);
        square.dome("red");
        assertEquals(true, square.ok());
    }

    @Test 
    public void ShouldmakeInvisible(){
        //square = new Square(1500,1500,10);
        square.makeInvisible();
        assertEquals(false, square.isVisible);
    }

    /*
    @Test 
    public void ShouldmakeVisible(){
    //square = new Square(1500,1500,10);
    square.makeVisible();
    assertEquals(true, square.ok());
    }
     */

    @Test
    public void ShouldwhoRequestedPhoto(){
        square.addDome("red",500, 20);
        square.addDome("blue", 500 , 200);
        square.addDome("green",500,500);
        String[] requestedPhoto = {"red","blue","green"};
        square.defineRequestedPhoto(requestedPhoto);
        square.touristArrive("orange", 20,200);
        String[] shouldBe = {"orange"};
        assertEquals(shouldBe,square.whoRequestedPhoto());
    }

    @Test 
    public void shouldTakeRequestedPhoto(){
        square.addDome("red",500, 20);
        square.addDome("blue", 500 , 200);
        square.addDome("green",500,500);
        square.touristArrive("orange", 20,200);
        String[] shouldBe = {"green","blue","red"};
        assertEquals(true,square.ok());
    }

    @Test
    public void shouldPerfectionist(){
        square.addDome("red", 200, 20);
        square.addDome("blue", 200, 70);
        String[] requestedPhoto = {"blue","red"} ;
        square.defineRequestedPhoto(requestedPhoto);
        square.touristArrive("perfectionist", "white", 100,100);
        assertEquals(null, square.touristTakePhoto("white"));
    }

    @Test
    public void shouldPrudent(){
        square.touristArrive("red", 0, 0);
        square.touristArrive("prudent","blue", 0, 19);
        assertEquals(true, square.ok());
    }

    @Test
    public void shouldFixed(){
        square.addDome("fixed","red", 200, 20);
        String[] shouldHave = {"red"};
        assertEquals(shouldHave,square.domes());
        square.delDome("red");
        assertEquals(shouldHave,square.domes());
    }

    @Test
    public void shouldShy(){
        square.addDome("shy","red", 500, 500);
        square.touristArrive("white", 300, 500);
        assertEquals(null,square.touristTakePhoto("white"));
        assertEquals(null,square.touristTakePhoto("white", 270));
        assertEquals(null,square.touristTakePhoto("white", 90));
        assertEquals(null,square.touristTakePhoto("white", 180));
    }

    @Test
    public void shouldTakeFixed(){
        square.addDome("fixed","red", 200, 20);
        square.addDome("normal","blue", 200, 70);
        square.touristArrive("takeFixed", "white", 100,100);
        String[] requestedPhoto = {"red"} ;
        assertEquals(requestedPhoto, square.touristTakePhoto("white"));
    }

    @After
    public void delete(){
        //System.exit(0);
    }

}
