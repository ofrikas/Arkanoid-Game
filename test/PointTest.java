import geometry.Point;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point p1,p2,p3,p4,p5,p6,p7;
    @BeforeEach
    void setUp() {
         p1 = new Point(3.5, 4.6);
         p2 = new Point(3.5, 4.6);
         p3 = new Point(5.4, 7.8);
         p4 = new Point(-5.4, 7.8);
         p5 = new Point(-5.4, -7.8);
         p6 = new Point(5.4, -7.8);
         p7 = new Point(0.0, 0.0);
    }

    @AfterEach
    void tearDown() {
        System.out.println("passed tests for geometry.Point");
    }

    @Test
    void distance() {
        assert (p1.distance(p1) == 0.0);
        assert (p1.distance(p2) == 0.0);
        assert(isInRangeOfEpsilon(p1.distance(p3),Math.sqrt(Math.pow(1.9, 2) + Math.pow(3.2, 2))));
        assert ((int)p1.distance(p3) == 3);
        assert(isInRangeOfEpsilon(p1.distance(p4),Math.sqrt(Math.pow(-8.9, 2) + Math.pow(3.2, 2))));
        assert(isInRangeOfEpsilon(p1.distance(p5),Math.sqrt(Math.pow(-8.9, 2) + Math.pow(-12.4, 2))));
        assert(isInRangeOfEpsilon(p1.distance(p6),Math.sqrt(Math.pow(1.9, 2) + Math.pow(-12.4, 2))));
    }

    @Test
    void equals() {
        assert (p1.equals(p1));
        assert (p1.equals(p2));
        assert (!p1.equals(p3));
    }



    @Test
    void getX() {
        assert (p1.getX() == 3.5);
    }

    @Test
    void getY() {
        assert (p7.getY() == 0);
        assert (p1.getY() == 4.6);
    }

    @Test
    void incline() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 6);
        double expectedIncline = 1;
        double actualIncline = p1.incline(p2);
        double epsilon = 0.0001;
        assertEquals(expectedIncline, actualIncline, epsilon);
    }
    public boolean isInRangeOfEpsilon(double x, double y) {
        double epsilon = 0.000000000000001;
        return x >= y - epsilon && x <= y + epsilon;
    }
}