import game.Velocity;
import geometry.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VelocityTest {
    private Velocity v1,v2,v3,v4,v5,v6,v7,v8;
    private Point p1,p2,p3,p4;

    @BeforeEach
    void setUp() {
         v1 = new Velocity(3.5, 6.7);
         v2 = new Velocity(-3.5, 6.7);
         v3 = new Velocity(-3.5, -6.7);
         v4 = new Velocity(3.5, -6.7);
         v5 = new Velocity(0.0, 0.0);
         v6 = Velocity.fromAngleAndSpeed(90.0, 5.4);
         v7 = Velocity.fromAngleAndSpeed(0.0, 5.4);
         v8 = Velocity.fromAngleAndSpeed(30.0, 6.0);

         p1 = new Point(3.5, 7.8);
         p2 = new Point(-3.5, 7.8);
         p3 = new Point(-3.5, -7.8);
         p4 = new Point(3.5, -7.8);
    }
    @Test
    void fromAngleAndSpeed() {
    }

    @Test
    void getDx() {
    }

    @Test
    void setDx() {
    }

    @Test
    void getDy() {
    }

    @Test
    void setDy() {
    }

    @Test
    void applyToPoint() {
        assert (v1.applyToPoint(p1).equals(new Point(7.0, 14.5)));
        assert (v1.applyToPoint(p2).equals(new Point(0.0, 14.5)));
        assert (v1.applyToPoint(p3).equals(new Point(0.0, -1.1)));
        assert (v1.applyToPoint(p4).equals(new Point(7.0, -1.1)));

        assert (v2.applyToPoint(p1).equals(new Point(0.0, 14.5)));
        assert (v2.applyToPoint(p2).equals(new Point(-7.0, 14.5)));
        assert (v2.applyToPoint(p3).equals(new Point(-7.0, -1.1)));
        assert (v2.applyToPoint(p4).equals(new Point(0.0, -1.1)));

        assert (v3.applyToPoint(p1).equals(new Point(0.0, 1.1)));
        assert (v3.applyToPoint(p2).equals(new Point(-7.0, 1.1)));
        assert (v3.applyToPoint(p3).equals(new Point(-7.0, -14.5)));
        assert (v3.applyToPoint(p4).equals(new Point(0.0, -14.5)));

        assert (v4.applyToPoint(p1).equals(new Point(7.0, 1.1)));
        assert (v4.applyToPoint(p2).equals(new Point(0.0, 1.1)));
        assert (v4.applyToPoint(p3).equals(new Point(0.0, -14.5)));
        assert (v4.applyToPoint(p4).equals(new Point(7.0, -14.5)));

        assert (v5.applyToPoint(p1).equals(new Point(3.5, 7.8)));
        assert (v5.applyToPoint(p2).equals(new Point(-3.5, 7.8)));
        assert (v5.applyToPoint(p3).equals(new Point(-3.5, -7.8)));
        assert (v5.applyToPoint(p4).equals(new Point(3.5, -7.8)));
        //geometry.Point p4 = new geometry.Point(3.5, -7.8);
        assert (v6.applyToPoint(p4).equals(new Point(8.9, -7.8)));
        // assert (v7.applyToPoint(p4).equals(new geometry.Point(3.5, -2.4)));
        assert (v7.applyToPoint(p4).equals(new Point(3.5, -13.2)));
        // assert (v8.applyToPoint(p4).equals(new geometry.Point(((6.0 * Math.sin(Math.PI/6)))
        // + 3.5, (6.0 * Math.sin(Math.PI / 3)) -7.8)));
        assert (v8.applyToPoint(p4).equals(new Point(((6.0 * Math.sin(Math.PI/6)))
                + 3.5, -(6.0 * Math.sin(Math.PI / 3)) -7.8)));
    }
}