import game.GameLevel;
import game.GameLevelGreen3;
import game.Velocity;
import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    private Block bl;
    private Rectangle rec;

    @BeforeEach
    void setUp() {
        rec = new Rectangle(new Point(0,50),50,50);
        bl = new Block(rec);
    }

    @Test
    void getRec() {
        assertTrue(rec.getUpperLeft().equals(bl.getRec().getUpperLeft()));
        assertTrue(rec.getHeight()==bl.getRec().getHeight());
        assertTrue(rec.getWidth()==bl.getRec().getWidth());
    }

    @Test
    void getCollisionRectangle() {
    }

    @Test
    void hit() {
        Velocity vel = new Velocity(1,1);
        Velocity v1 =  bl.hit(null,new Point(0,50),vel); //top left corner
        assertTrue(v1.getDx()==-1);
        assertTrue(v1.getDy()==-1);
        vel = new Velocity(-1,1);
        v1 =  bl.hit(null,new Point(50,50),vel); //top right corner
        assertTrue(v1.getDx()==1);
        assertTrue(v1.getDy()==-1);
        vel = new Velocity(8,-8);
        v1 =  bl.hit(null,new Point(0,100),vel); //bottom left corner
        assertTrue(v1.getDx()==-8);
        assertTrue(v1.getDy()==8);
        vel = new Velocity(-8,-8);
        v1 =  bl.hit(null,new Point(50,100),vel); //bottom right corner
        assertTrue(v1.getDx()==8);
        assertTrue(v1.getDy()==8);
        vel = new Velocity(8,8);
        v1 =  bl.hit(null,new Point(8.5,50),vel); //top Horizon
        assertTrue(v1.getDx()==8);
        assertTrue(v1.getDy()==-8);
        vel = new Velocity(-8,8);
        v1 =  bl.hit(null,new Point(8.5,50),vel); //top Horizon
        assertTrue(v1.getDx()==-8);
        assertTrue(v1.getDy()==-8);
        vel = new Velocity(8,-8);
        v1 =  bl.hit(null,new Point(9.3,100),vel); //bottom Horizon
        assertTrue(v1.getDx()==8);
        assertTrue(v1.getDy()==8);
        vel = new Velocity(-8,-8);
        v1 =  bl.hit(null,new Point(9.3,100),vel); //bottom Horizon
        assertTrue(v1.getDx()==-8);
        assertTrue(v1.getDy()==8);
        vel = new Velocity(8,-8);
        v1 =  bl.hit(null,new Point(0,99.857),vel); //left vertical
        assertTrue(v1.getDx()==-8);
        assertTrue(v1.getDy()==-8);
        vel = new Velocity(8,8);
        v1 =  bl.hit(null,new Point(0,99.857),vel); //left vertical
        assertTrue(v1.getDx()==-8);
        assertTrue(v1.getDy()==8);
        vel = new Velocity(-8,8);
        v1 =  bl.hit(null,new Point(50,98.7563),vel); //right vertical
        assertTrue(v1.getDx()==8);
        assertTrue(v1.getDy()==8);
        vel = new Velocity(-8,-8);
        v1 =  bl.hit(null,new Point(50,98.7563),vel); //right vertical
        assertTrue(v1.getDx()==8);
        assertTrue(v1.getDy()==-8);
    }

//    @Test
//    public void testRemoveFromGame_SuccessfulRemoval() {
//        // Create a game instance and add the collidable and sprite to it
//        GameLevelGreen3 gl3 = new GameLevelGreen3();
//        GameLevel gameLevel = new GameLevel(gl3);
//        Block b = new Block(new Rectangle(new Point(10,10),10,10));
//        b.addToGame(gameLevel);
//
//
//        // Remove the collidable and sprite from the game
//        boolean result = b.removeFromGame(gameLevel);
//
//        // Assert that the removal was successful
//        assertTrue(result);
//        assertFalse(gameLevel.getEnvironment().getListOfCollidable().contains(b));
//        assertFalse(gameLevel.getSprites().getLOS().contains(b));
//    }
//
//    @Test
//    public void testRemoveFromGame_UnsuccessfulRemoval() {
//        // Create a game instance without adding the collidable and sprite
//        GameLevelGreen3 gl3 = new GameLevelGreen3();
//        GameLevel gameLevel = new GameLevel(gl3);
//        Block b = new Block(new Rectangle(new Point(10,10),10,10));
//
//        // Remove the collidable and sprite from the game
//        boolean result = b.removeFromGame(gameLevel);
//
//        // Assert that the removal was unsuccessful
//        assertFalse(result);
//    }
}