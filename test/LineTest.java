import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LineTest {
    private Line f, l2f, g, h, i, j, k, l, m, n, p, q, r, s, a, b, j1, k1, l1, m1, n1, f1, g1, t1, s1, r1, h1, lh1, i1, li1;
    private Point c1, c2;

    @BeforeEach
    void setUp() {
        f = new Line(49.45, 68.3, 93.23, 27.3);
        c1 = new Point(49.45, 68.3);
        c2 = new Point(93.23, 27.3);
        // is same as lf
        l2f = new Line(c1, c2);
        g = new Line(151.94528, 65.76442, 54.76838, 16.41678);
        // l4 and l5 parallel to y axis but don't touch
        h = new Line(20.0, 140.0, 20.0, 120.0);
        i = new Line(20.0, 100.0, 20.0, 60.0);
        j = new Line(71.72, 55.3, 80.07, 64.24);
        k = new Line(70.52, 93.75, 65.46, 62.00);
        // l8, l9 are parallel to Y axis and share one point
        l = new Line(40.0, 140.0, 40.0, 100.0);
        m = new Line(40.0, 100.0, 40.0, 80.0);
        // l10, l11 are parallel to X axis and share one point
        n = new Line(60.0, 40.0, 40.0, 40.0);
        p = new Line(40.0, 40.0, 20.0, 40.0);
        // l12, l13 are parallel to X axis and don't share any points
        q = new Line(60.0, 120.0, 80.0, 120.0);
        r = new Line(100.0, 120.0, 120.0, 120.0);
        s = new Line(102.04805, 77.17004, 119.96, 49.52);
        // geometry.Line t = new geometry.Line(136.24, 57.79,180.0, 80.04);
        // a is on y axis
        a = new Line(0.0, 0.0, 0.0, 40.0);
        b = new Line(9.13, 31.2, -23.16, 16.44);
        // j1 is on x axis
        j1 = new Line(0.0, 0.0, 160.0, 0.0);
        //intersects with x axis
        k1 = new Line(120.0, 20.0, 120.0, -20.0);
        l1 = new Line(100.0, 20.0, 160.0, -20.0);
        m1 = new Line(40.0, 60.0, 40.0, 20.0);
        n1 = new Line(00.0, 80.0, 40.0, 80.0);
        f1 = new Line(80.0, 164.0, 90.0, 184.0);
        g1 = new Line(85.0, 174.0, 95.0, 194.0);
        Point t1 = new Point(100.0, 160.0);
        Point s1 = new Point(120.0, 160.0);
        Point r1 = new Point(1400.0, 160.0);
        //are the same but start point and end point are opposite
        h1 = new Line(t1, s1);
        lh1 = new Line(s1, t1);
        //are the same but start point and end point are opposite
        i1 = new Line(t1, r1);
        li1 = new Line(r1, t1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void length() {
        assert (f.length() == c1.distance(c2));
    }

    @Test
    void middle() {
        assert (f.middle().equals(new Point(71.34, 47.8)));
    }

    @Test
    void start() {
        assert (f.start().equals(c1));
    }

    @Test
    void end() {
        assert (f.end().equals(c2));
    }

    @Test
    void isIntersecting() {
        assert (f.isIntersecting(l2f));//same line
        assert (f.isIntersecting(g));
        // same but opposite order
        assert (g.isIntersecting(f));
        // both parallel to y but don't touch
        assert (!h.isIntersecting(i));
        // parallel to each other
        assert (!h.isIntersecting(l));
        // perpendicular to each other, but don't touch
        assert (!i.isIntersecting(p));
        // have one in common**********************************************
        assert (l.isIntersecting(m));
        // have one in common
        assert (n.isIntersecting(p));
        assert (!q.isIntersecting(r));
        assert (!l2f.isIntersecting(k));
        assert (!l2f.isIntersecting(j));
        assert (s.isIntersecting(g));
        //assert (l15.isIntersecting(l3));
        assert (f1.isIntersecting(g1));
        assert (!k.isIntersecting(q));
        assert (b.isIntersecting(a));
        assert (a.isIntersecting(j1));
        assert (k1.isIntersecting(j1));
        assert (n1.isIntersecting(i));
        assert (k1.isIntersecting(l1));
        assert (m1.isIntersecting(p));
        assert (j1.isIntersecting(l1));

    }

    @Test
    void intersectionWith() {
        assert (f.intersectionWith(f) == null);
        assert (f.intersectionWith(l2f) == null);
        // assert (l3.intersectionWith(l15) == null);
        assert (f1.intersectionWith(g1) == null);
        assert (k.intersectionWith(f) == null);
        // lines that have other line completely in them,
        // wildest sharing a start or end point
        assert (h1.intersectionWith(lh1) == null);
        assert (h1.intersectionWith(i1) == null);
        assert (h1.intersectionWith(li1) == null);
        assert (lh1.intersectionWith(h1) == null);
        assert (lh1.intersectionWith(i1) == null);
        assert (lh1.intersectionWith(li1) == null);
        assert (i1.intersectionWith(h1) == null);
        assert (i1.intersectionWith(lh1) == null);
        assert (i1.intersectionWith(li1) == null);
        assert (li1.intersectionWith(lh1) == null);
        assert (li1.intersectionWith(i1) == null);
        assert (li1.intersectionWith(h1) == null);
        assert (n.intersectionWith(p).equals(new Point(40.0, 40.0)));
        assert (s.intersectionWith(g).equals(new Point(119.95907554822693, 49.521427043314844)));
        assert (f.intersectionWith(g).equals(new Point(87.242330580114, 32.907456514740204)));
        assert (a.intersectionWith(b).equals(new Point(0.0, 27.02660885723134)));
        assert (j1.intersectionWith(l1).equals(new Point(130.0, 0.0)));
        assert (m1.intersectionWith(p).equals(new Point(40.0, 40.0)));
        assert (k1.intersectionWith(l1).equals(new Point(120.0, 6.666666666666657)));
        assert (k1.intersectionWith(j1).equals(new Point(120.0, 0.0)));
        assert (n1.intersectionWith(i).equals(new Point(20.0, 80.0)));
        assert (a.intersectionWith(j1).equals(new Point(0.0, 0.0)));
    }

    @Test
    void testEquals() {
        assert (f.equals(f));
        assert (f.equals(l2f));
        assert (!f.equals(g));
    }

    @Test
    void xInRange() {
    }

    @Test
    void closestIntersectionToStartOfLine() {
        Point upperLeft = new Point(50,50);
        Rectangle rect1 = new Rectangle(upperLeft,50,50);
        Line l4 = new Line(new Point(49,49),new Point(0,0));
        Line l5 = new Line(new Point(49,49),new Point(0,49));
        Line l6 =new Line(new Point(100.5,-100),new Point(100.2,50));
        Line l7 =new Line(new Point(50.3,100.3),new Point(50.2,100.3));
        Line l8 =new Line(new Point(1,49.9),new Point(100.2,49.99));
        assertNull(l4.closestIntersectionToStartOfLine(rect1));
        assertNull(l5.closestIntersectionToStartOfLine(rect1));
        assertNull(l6.closestIntersectionToStartOfLine(rect1));
        assertNull(l7.closestIntersectionToStartOfLine(rect1));
        assertNull(l8.closestIntersectionToStartOfLine(rect1));
        Line l9 = new Line(new Point(50,50),new Point(0,0));
        Line l10 = new Line(new Point(51,25),new Point(100,50));
        Line l11=new Line(new Point(65,100),new Point(100,50));
        Line l12=new Line(new Point(25,75),new Point(75,125));
        Line l13=new Line(new Point(100,50),new Point(1000,-345));
        assertTrue(new Point(50,50).equals(l9.closestIntersectionToStartOfLine(rect1)));
        Point p3 = l10.closestIntersectionToStartOfLine(rect1);
        assertTrue(new Point(100,50).equals(p3));
        Point p4 = l11.closestIntersectionToStartOfLine(rect1);
        assertTrue(new Point(65,100).equals(p4));
        assertTrue(new Point(50,100).equals(l12.closestIntersectionToStartOfLine(rect1)));
        assertTrue(new Point(100,50).equals(l13.closestIntersectionToStartOfLine(rect1)));
        //2 dots
        Line l14 = new Line(new Point(75,0),new Point(75,150));
        Line l15 = new Line(new Point(75,150),new Point(75,0));
        Line l16=new Line(new Point(0,75.8),new Point(300,75.8));
        Line l17=new Line(new Point(300,75.8),new Point(0,75.8));
        Line l18=new Line(new Point(50,50),new Point(100,100));
        Line l19=new Line(new Point(100,100),new Point(50,50));
        assertTrue(new Point(75,50).equals(l14.closestIntersectionToStartOfLine(rect1)));
        Point p5 = l15.closestIntersectionToStartOfLine(rect1);
        assertTrue(new Point(75,100).equals(p5));
        Point p6 = l16.closestIntersectionToStartOfLine(rect1);
        assertTrue(new Point(50,75.8).equals(p6));
        assertTrue(new Point(100,75.8).equals(l17.closestIntersectionToStartOfLine(rect1)));
        assertTrue(new Point(50,50).equals(l18.closestIntersectionToStartOfLine(rect1)));
        assertTrue(new Point(100,100).equals(l19.closestIntersectionToStartOfLine(rect1)));
        // on upon the other
        Line l20 = new Line(new Point(100,50),new Point(50,50));
        Line l21 = new Line(new Point(50,100),new Point(50,50));
        Line l22=new Line(new Point(100,100),new Point(50,100));
        Line l23=new Line(new Point(100,100),new Point(100,50));
        assertTrue(new Point(100,50).equals(l20.closestIntersectionToStartOfLine(rect1)));
        assertTrue(new Point(50,100).equals(l21.closestIntersectionToStartOfLine(rect1)));
        assertTrue(new Point(100,100).equals(l22.closestIntersectionToStartOfLine(rect1)));
        assertTrue(new Point(100,100).equals(l23.closestIntersectionToStartOfLine(rect1)));
    }

    @Test
    void onUponTheOther() {
        assertTrue(g.onUponTheOther(g));
        assertTrue(h.onUponTheOther(h));
        assertTrue(i.onUponTheOther(i));
        assertTrue(j.onUponTheOther(j));
        assertTrue(k.onUponTheOther(k));
        assertTrue(l.onUponTheOther(l));
        assertTrue(m.onUponTheOther(m));
        assertTrue(n.onUponTheOther(n));
        assertFalse(h.onUponTheOther(i));
        assertFalse(f.onUponTheOther(g));
        assertFalse(s.onUponTheOther(g));
        assertFalse(g.onUponTheOther(s));

    }

    @Test
    void isPointInsideLine() {
        Line l1 = new Line(new Point(0,0),new Point(100,100));
        Line l2 = new Line(new Point(0,0),new Point(0,100));
        Line l3 = new Line(new Point(0,0),new Point(100,0));
        assertTrue(l1.isPointInsideLine(new Point(2,2)));
        assertTrue(l1.isPointInsideLine(new Point(5,5)));
        assertTrue(l1.isPointInsideLine(new Point(100,100)));
        assertTrue(l1.isPointInsideLine(new Point(99.5,99.5)));
        assertFalse(l1.isPointInsideLine(new Point(1,2)));
        assertFalse(l1.isPointInsideLine(new Point(101,101)));
        assertTrue(l2.isPointInsideLine(new Point(0,0)));
        assertTrue(l2.isPointInsideLine(new Point(0,20)));
        assertTrue(l2.isPointInsideLine(new Point(0,40.8)));
        assertTrue(l2.isPointInsideLine(new Point(0,100)));
        assertFalse(l2.isPointInsideLine(new Point(1,2)));
        assertFalse(l2.isPointInsideLine(new Point(0.1,0)));
        assertTrue(l3.isPointInsideLine(new Point(0,0)));
        assertTrue(l3.isPointInsideLine(new Point(20,0)));
        assertTrue(l3.isPointInsideLine(new Point(40.8,0)));
        assertTrue(l3.isPointInsideLine(new Point(99.9876,0)));
        assertFalse(l3.isPointInsideLine(new Point(99.9876,-0.5)));
        assertFalse(l3.isPointInsideLine(new Point(-0.1,0)));
    }
}