import geometry.Sprite;
import game.SpriteCollection;
import geometry.Block;
import geometry.Point;
import geometry.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpriteCollectionTest {
    private SpriteCollection sC;
    private Sprite s;

    @BeforeEach
    void setUp() {
        sC = new SpriteCollection();
        s = new Block(new Rectangle(new Point(0,0),20,30));
    }

    @Test
    void addSprite() {
        sC.addSprite(s);
        assertFalse(sC.getLOS().isEmpty());
    }

    @Test
    void getLOS() {
        sC.addSprite(s);
        assertEquals(s, sC.getLOS().get(0));
    }

    @Test
    void notifyAllTimePassed() {

    }

    @Test
    void drawAllOn() {
    }
}