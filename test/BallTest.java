import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

class BallTest {

    @BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void getX() {

    }

    @org.junit.jupiter.api.Test
    void getY() {

    }

    @org.junit.jupiter.api.Test
    void getSize() {

    }

    @org.junit.jupiter.api.Test
    void getColor() {

    }

    @org.junit.jupiter.api.Test
    void drawOn() {

    }

    @org.junit.jupiter.api.Test
    void setVelocity() {

    }

    @org.junit.jupiter.api.Test
    void getVelocity() {
    }

    @org.junit.jupiter.api.Test
    void testSetVelocity() {
    }

    @org.junit.jupiter.api.Test
    void moveOneStep() {

    }

    @org.junit.jupiter.api.Test
    void testMoveOneStep() {

    }

//    @Test
//    void old() {
//        geometry.Point c1 = new geometry.Point(50.5, 60.3);
//        geometry.Point c2 = new geometry.Point(0.0, 0.0);
//        geometry.Point c3 = new geometry.Point(-90.8, 130.5);
//        geometry.Point c4 = new geometry.Point(-90.8, -130.5);
//        geometry.Point c5 = new geometry.Point(90.8, -130.5);
//        geometry.Point c6 = new geometry.Point(90.8, 130.5);
//        geometry.Point c7= new geometry.Point(150.0,150.0);
//        geometry.Point c8= new geometry.Point(100.0,100.0);
//
//
//        geometry.Ball b1 = new geometry.Ball(c1, 5, Color.PINK);
//        geometry.Ball b2 = new geometry.Ball(c1, 5, Color.BLACK);
//        geometry.Ball b3 = new geometry.Ball(c1, 6, Color.PINK);
//        geometry.Ball b4 = new geometry.Ball(c2, 5, Color.PINK);
//        geometry.Ball b5 = new geometry.Ball(c3, 23, Color.MAGENTA);
//        geometry.Ball b6 = new geometry.Ball(c4, 13, Color.MAGENTA);
//        geometry.Ball b7 = new geometry.Ball(c5, 3, Color.CYAN);
//        geometry.Ball b8 = new geometry.Ball(c6, 9, Color.ORANGE);
//
//        geometry.Ball b9 = new geometry.Ball(c7, 50, Color.BLUE);//only x velocity changes
//        geometry.Ball b10 = new geometry.Ball(c8, 100, Color.GREEN);
//        geometry.Ball b11 = new geometry.Ball(c7, 50, Color.BLUE);//only y velocity changes
//        geometry.Ball b12 = new geometry.Ball(c7, 50, Color.BLUE);//both x and y changes
//
//        //check that program will not fail:
//        geometry.Ball b13 = new geometry.Ball(c6, -9, Color.ORANGE);   // radius is negative
//        geometry.Ball b14 = new geometry.Ball(c6, 150, Color.ORANGE);//radius bigger than 200*200 screen;
//        GUI u = new GUI("TestbadBalls", 200, 200);
//        DrawSurface a = u.getDrawSurface();
//        b13.drawOn(a);
//        b14.drawOn(a);
//        u.show(a);
//
//        assert (b1.getX() == 50);
//        assert (b1.getY() == 60);
//        assert (b1.getSize() == 5);
//        assert (b1.getColor() == Color.PINK);
//        assert (b2.getColor() == Color.BLACK);
//
//        GUI gui = new GUI("TestBalls1", 200, 200);
//        DrawSurface d = gui.getDrawSurface();
//        b1.drawOn(d);
//        // this will over ride b1
//        b2.drawOn(d);
//        //this will overide b2
//        b3.drawOn(d);
//        b4.drawOn(d);
//        //these balls are out of range of screen
//        b5.drawOn(d);
//        b6.drawOn(d);
//        b7.drawOn(d);
//        //the orange ball
//        b8.drawOn(d);
//        gui.show(d);
//
//        game.Velocity v1 = new game.Velocity(3.5, 6.7);
//        game.Velocity v2 = new game.Velocity(5.4, 8.9);
//        game.Velocity v3 = new game.Velocity(-6.7, 3.5);
//        game.Velocity v4 = new game.Velocity(3.5, -6.7);
//        game.Velocity v5 = new game.Velocity(3.5, 3.5);
//        b1.setVelocity(v4);
//
//        assert(b1.getVelocity() == v4);
//        b2.setVelocity(3.5,6.7);
//        assert (b2.getVelocity().getDx() == v1.getDx() && b2.getVelocity().getDy() == v1.getDy());
//        b2.setVelocity(5.4,8.9);
//        assert(b2.getVelocity().getDx() == v2.getDx() && b2.getVelocity().getDy() == v2.getDy());
//        b9.setVelocity(v4);
//        assert(b9.getVelocity().getDx() == v4.getDx() && b9.getVelocity().getDy() == v4.getDy());
//        b10.setVelocity(v2);
//        assert (b10.getVelocity().getDx() == v2.getDx() && b10.getVelocity().getDy() == v2.getDy());
//        b11.setVelocity(v3);
//        assert (b11.getVelocity().getDx() == v3.getDx() && b11.getVelocity().getDy() == v3.getDy());
//        b12.setVelocity(v5);
//        assert (b12.getVelocity().getDx() == v5.getDx() && b12.getVelocity().getDy() == v5.getDy());
//
//        b1.moveOneStep();
//        assert (b1.getX()== 54 && b1.getY()==53);
//
//        b3.moveOneStep();//check system doesn't crash if I move without setting velocity
//
//
//        b9.moveOneStep();
//        assert (b9.getX() == 146 && b9.getY()==143);
//
////            b10.moveOneStep();
////            assert (b9.getX() == 100 && b9.getY()==100);// piazza says no need to try
//
//        b11.moveOneStep();
//        assert (b11.getX() == 143 && b11.getY() == 146);
//
//        b12.moveOneStep();
//        assert (b12.getX() == 146 && b12.getY() == 146);
//
//        /*if wanted- print these balls as well.*/
////            GUI g = new GUI("TestBalls2", 200, 200);
////            DrawSurface t = gui.getDrawSurface();
////            b9.drawOn(t);
//////            b11.drawOn(t);
//////            b12.drawOn(t);
////            g.show(t);
//        System.out.println("passed tests for geometry.Ball old one");
    }
