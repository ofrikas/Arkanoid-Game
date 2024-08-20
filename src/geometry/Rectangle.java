/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * The geometry.Rectangle class represents a rectangle in 2D space,
 * with a location of the upper-left point, width, and height.
 * It provides methods for getting and setting various properties of the rectangle,
 * such as the upper-right, bottom-left, and bottom-right points,
 * the center point, the width, and the height.
 * It also provides methods for calculating the intersection points with a given line,
 * drawing the rectangle on a given DrawSurface, checking if another rectangle is equal to this rectangle,
 * and checking if a given point is inside the rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final Point upperRight;
    private final Point bottomLeft;
    private final Point bottomRight;

    private final Point center;
    private final double width;
    private final double height;
    //Lines that represents the borders
    private final Line topHorizontalLine, bottomHorizontalLine, leftVerticalLine, rightVerticalLine;

    /**
     * Creates a new rectangle with the specified upper-left point, width, and height.
     * The upper-right, bottom-left, and bottom-right points are calculated accordingly.
     * The center point is calculated as well.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        //Creat and get the dots of the rectangle
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.center = new Point((this.upperLeft.getX() + this.upperRight.getX()) / 2,
                (this.upperLeft.getY() + this.bottomLeft.getY()) / 2);
        this.width = width;
        this.height = height;
        //Creat borders Lines
        this.topHorizontalLine = new Line(this.upperLeft, this.upperRight);
        this.bottomHorizontalLine = new Line(this.bottomLeft, this.bottomRight);
        this.leftVerticalLine = new Line(this.upperLeft, this.bottomLeft);
        this.rightVerticalLine = new Line(this.upperRight, this.bottomRight);


    }

    /**
     * @return the upper-right point of the rectangle
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * @return the bottom-left point of the rectangle
     */
    public Point getBottomLeft() {
        return bottomLeft;
    }

    /**
     * @return the bottom-right point of the rectangle
     */
    public Point getBottomRight() {
        return bottomRight;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return the center point of the rectangle
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return the top horizontal line of the rectangle.
     */
    public Line getTopHorizontalLine() {
        return topHorizontalLine;
    }

    /**
     * @return the bottom horizontal line of the rectangle.
     */
    public Line getBottomHorizontalLine() {
        return bottomHorizontalLine;
    }

    /**
     * @return the left vertical line of the rectangle.
     */
    public Line getLeftVerticalLine() {
        return leftVerticalLine;
    }

    /**
     * @return the right vertical line of the rectangle.
     */
    public Line getRightVerticalLine() {
        return rightVerticalLine;
    }

    /**
     * Returns a (possibly empty) List of intersection points with the specified line.
     * The order of the list is from the closest point to the farthest one from it.
     *
     * @param line the line to check for intersection with the rectangle.
     * @return a list of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> lOP = new ArrayList<>(); //List Of points
        //Check for infinite number of points
        if (this.topHorizontalLine.onUponTheOther(line) || this.bottomHorizontalLine.onUponTheOther(line)
                || this.leftVerticalLine.onUponTheOther(line) || this.rightVerticalLine.onUponTheOther(line)) {
            return lOP;
        }
        Line oppositeLine = new Line(line.getSecDot(), line.getFirstDot());
        Point firstIntersection, secondIntersection;
        firstIntersection = line.closestIntersectionToStartOfLine(this);
        secondIntersection = oppositeLine.closestIntersectionToStartOfLine(this);
        //order is the order of the ifs
        if (firstIntersection != null) { //get 0 in the list
            lOP.add(firstIntersection);
        }
        if (secondIntersection != null) {
            lOP.add(secondIntersection);
        }
        return lOP;
    }

    /**
     * Draws the lines of the rectangle with no fill inside the rectangle.
     *
     * @param d the DrawSurface on which to draw the rectangle.
     */
    public void drawOn(DrawSurface d) {
        this.bottomHorizontalLine.drawOn(d);
        this.topHorizontalLine.drawOn(d);
        this.leftVerticalLine.drawOn(d);
        this.rightVerticalLine.drawOn(d);
    }

    /**
     * Checks if this rectangle is equal to another rectangle.
     *
     * @param rec the rectangle to compare to.
     * @return true if the rectangles are equal, false otherwise.
     */
    public boolean isEqual(Rectangle rec) {
        return this.upperLeft.equals(rec.getUpperLeft()) && this.upperRight.equals(rec.getUpperRight())
                && this.bottomRight.equals(rec.getBottomRight()) && this.bottomLeft.equals(rec.getBottomLeft());
    }

    /**
     * Checks if this point is inside or upon the lines of the rectangle.
     *
     * @param p the point we check if inside.
     * @return true if the point is inside, false otherwise.
     */
    public boolean isPointInside(Point p) {
        double x = p.getX();
        double y = p.getY();
        return x >= upperLeft.getX() && x <= upperRight.getX() && y >= upperRight.getY() && y <= bottomRight.getY();
    }
}