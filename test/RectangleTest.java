import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private Rectangle rec,rec2,rec3,rec4,rec5;
    @BeforeEach
    void setUp() {
        rec = new Rectangle(new Point(0,6),50,60);
        rec2 =  new Rectangle(new Point(0,50),50,50);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void intersectionPoints() {
        Point p =rec.intersectionPoints(new Line(new Point(0,6),new Point(50,66))).get(0);
        assertTrue(p.equals(new Point(0,6)),"intersectionPoints error");
        assertTrue(rec.intersectionPoints(new Line(new Point(0,6),new Point(50,66))).get(1).equals(new Point(50,66)),"intersectionPoints error");
        assertTrue(rec2.intersectionPoints(new Line(new Point(10,10),new Point(100.3,100.3))).get(0).equals(new Point(50,50)),"intersectionPoints error");
        List l = rec2.intersectionPoints(new Line(new Point(0,49),new Point(50,49.9)));
        assertTrue(l.isEmpty(),"intersectionPoints error");
        List l2 = rec2.intersectionPoints(new Line(new Point(51,50),new Point(50.1,100)));
        List l3 = rec2.intersectionPoints(new Line(new Point(50,100.3),new Point(0,100.1)));
        List l4 = rec2.intersectionPoints(new Line(new Point(-0.5,100),new Point(-0.1,50)));
        assertTrue(l2.isEmpty(),"intersectionPoints error");
        assertTrue(l3.isEmpty(),"intersectionPoints error");
        assertTrue(l4.isEmpty(),"intersectionPoints error");
        assertTrue(rec2.intersectionPoints(new Line(new Point(0,96),new Point(50,90))).get(0).equals(new Point(0,96)),"intersectionPoints error");
        assertTrue(rec2.intersectionPoints(new Line(new Point(50,90),new Point(0,96))).get(0).equals(new Point(50,90)),"intersectionPoints error");
        assertTrue(rec2.intersectionPoints(new Line(new Point(-30,90),new Point(50,96))).get(1).equals(new Point(50,96)),"intersectionPoints error");
        assertTrue(rec2.intersectionPoints(new Line(new Point(-30,90),new Point(50,90))).get(0).equals(new Point(0,90)),"intersectionPoints error");
        List l5 = rec2.intersectionPoints(new Line(new Point(0,50),new Point(50,50)));
        List l6 = rec2.intersectionPoints(new Line(new Point(50,50),new Point(50,100)));
        List l7 = rec2.intersectionPoints(new Line(new Point(50,100),new Point(0,100)));
        List l8 = rec2.intersectionPoints(new Line(new Point(0,100),new Point(0,50)));
        assertTrue(l5.isEmpty(),"intersectionPoints error");
        assertTrue(l6.isEmpty(),"intersectionPoints error");
        assertTrue(l7.isEmpty(),"intersectionPoints error");
        assertTrue(l8.isEmpty(),"intersectionPoints error");
    }

    @Test
    void getWidth() {
        assertEquals(50,rec.getWidth(),"getWidth is not correct");
    }

    @Test
    void getHeight() {
        assertEquals(60,rec.getHeight(),"getHeight is not correct");
    }

    @Test
    void getUpperLeft() {
        assertTrue(rec.getUpperLeft().equals(new Point(0,6)));
    }
    void getUpperRight() {
        assertTrue(new Point(50,50).equals(rec2.getUpperRight()));
    }
    @Test
    void getBottomLeft() {
        assertTrue(new Point(0,100).equals(rec2.getBottomLeft()));
    }
    @Test
    void getBottomRight() {
        assertTrue(new Point(50,100).equals(rec2.getBottomRight()));
    }

    @Test
    void getTopHorizontalLine() {
        assertTrue(rec2.getUpperLeft().equals(rec2.getTopHorizontalLine().start()));
        assertTrue(rec2.getUpperRight().equals(rec2.getTopHorizontalLine().end()));
    }
    @Test
    void getBottomHorizontalLine() {
        assertTrue(rec2.getBottomLeft().equals(rec2.getBottomHorizontalLine().start()));
        assertTrue(rec2.getBottomRight().equals(rec2.getBottomHorizontalLine().end()));
    }
    @Test
    void getLeftVerticalLine() {
        Point p4 = rec2.getLeftVerticalLine().start();
        assertTrue(rec2.getUpperLeft().equals(p4));
        assertTrue(rec2.getBottomLeft().equals(rec2.getLeftVerticalLine().end()));
    }
    @Test
    void getRightVerticalLine() {
        assertTrue(rec2.getUpperRight().equals(rec2.getRightVerticalLine().start()));
        assertTrue(rec2.getBottomRight().equals(rec2.getRightVerticalLine().end()));
    }

}