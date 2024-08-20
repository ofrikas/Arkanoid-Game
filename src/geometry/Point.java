/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

/**
 * The geometry.Point class represents a 2D point in space.
 * It has an x and y coordinate which can be accessed using the getX() and getY() methods.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructs a new geometry.Point object with the given x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Compares this point to another point to see if they are equal.
     *
     * @param other the other point to compare to
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double threshold = 0.0001;
        return Math.abs(this.x - other.x) <= threshold && Math.abs(this.y - other.y) <= threshold;
    }

    /**
     * Gets the x coordinate of this point.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate of this point.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Calculates the incline between this point and another point.
     *
     * @param other the other point to calculate the incline to
     * @return the incline between this point and the other point
     */
    public double incline(Point other) {
        return (this.y - other.y) / (this.x - other.x);
    }
}