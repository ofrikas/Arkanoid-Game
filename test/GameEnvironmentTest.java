import game.CollisionInfo;
import game.GameEnvironment;
import geometry.Block;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEnvironmentTest {
    private GameEnvironment gE;

    @BeforeEach
    void setUp() {
        this.gE = new GameEnvironment();
    }

    @Test
    void getListOfCollidable() {
    }

    @Test
    void addCollidable() {
    }

    @Test
    void getClosestCollision() {
        Block left = new Block(new Rectangle(new Point(210,300),50,30));
        Block right = new Block(new Rectangle(new Point(260,300),50,30));
        gE.addCollidable(left);
        gE.addCollidable(right);
        Line trajectory = new Line(274,282,260,300);
        CollisionInfo colInf = gE.getClosestCollision(trajectory);
        assertTrue(colInf.collisionPoint().equals(new Point(260,300)));
        assertEquals(right,colInf.collisionObject());

        left = new Block(new Rectangle(new Point(260,130),50,35));
        right = new Block(new Rectangle(new Point(310,130),50,35));
        gE.addCollidable(right);
        gE.addCollidable(left);
        trajectory = new Line(242,115,260,130);
        colInf = gE.getClosestCollision(trajectory);
        assertTrue(colInf.collisionPoint().equals(new Point(260,130)));
        assertEquals(left,colInf.collisionObject());

        // Creating two blocks and adding them to the game environment
        Block block1 = new Block(new Rectangle(new Point(100, 100), 50, 50));
        Block block2 = new Block(new Rectangle(new Point(200, 200), 50, 50));
        GameEnvironment gameEnv = new GameEnvironment();
        gameEnv.addCollidable(block1);
        gameEnv.addCollidable(block2);

        // Creating a trajectory that doesn't intersect any collidables
        Line trajectory1 = new Line(50, 50, 75, 75);
        assertNull(gameEnv.getClosestCollision(trajectory1));

        // Creating a trajectory that intersects one collidable
        Line trajectory2 = new Line(50, 50, 125, 125);
        CollisionInfo collisionInfo2 = gameEnv.getClosestCollision(trajectory2);
        assertNotNull(collisionInfo2);
        assertTrue (collisionInfo2.collisionPoint().equals( new Point(100, 100)));
        assertEquals(collisionInfo2.collisionObject(), block1);

        // Creating a trajectory that intersects two collidables, but is closer to the second one
        Line trajectory3 = new Line(151, 151, 250, 250);
        CollisionInfo collisionInfo3 = gameEnv.getClosestCollision(trajectory3);
        assertNotNull(collisionInfo3);
        assertTrue(collisionInfo3.collisionPoint().equals( new Point(200, 200)));
        assertEquals(collisionInfo3.collisionObject(), block2);

        // Creating a trajectory that intersects two collidables, but is closer to the first one
        Line trajectory4 = new Line(200, 100, 50, 250);
        CollisionInfo collisionInfo4 = gameEnv.getClosestCollision(trajectory4);
        assertNotNull(collisionInfo4);
        assertEquals(collisionInfo4.collisionObject(), block1);
    }
}