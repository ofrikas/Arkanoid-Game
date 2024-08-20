/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Point;

/**
 * The game.Velocity class defines a 2D vector with both magnitude and direction,
 * represented by its dx (horizontal component) and dy (vertical component).
 * new positions after a certain amount of time has passed.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor of the game.Velocity class.
     *
     * @param dx the horizontal component of the velocity vector.
     * @param dy the vertical component of the velocity vector.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Create a new velocity from an angle and a speed.
     *
     * @param angle the angle of the vector in degrees.
     * @param speed the magnitude of the vector.
     * @return a new game.Velocity object with the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians); // negative because up is angle 0
        return new Velocity(dx, dy);
    }

    /**
     * Get the horizontal component of the velocity vector.
     *
     * @return the value of the dx field.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Set the horizontal component of the velocity vector.
     *
     * @param dx the new value of the dx field.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Get the vertical component of the velocity vector.
     *
     * @return the value of the dy field.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set the vertical component of the velocity vector.
     *
     * @param dy the new value of the dy field.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Apply the velocity vector to a given point, and return a new point
     * with the new coordinates.
     *
     * @param p the original point.
     * @return a new geometry.Point object with the new coordinates.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
