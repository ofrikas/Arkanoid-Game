/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A geometry.Line represents a straight line segment in 2D space, defined by two Points.
 */
public class Line implements Sprite {
    private final Point start;
    private final Point end;


    private final Point firstDot, secDot; //Used to save the first point inserted by the user

    /**
     * Constructs a geometry.Line with the specified start and end Points.
     * The function makes the lowest value of x to be the starting point.
     *
     * @param start the starting geometry.Point of the line
     * @param end   the ending geometry.Point of the line
     */
    public Line(Point start, Point end) {
        this.firstDot = start;
        this.secDot = end;
        if (start.getX() <= end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.end = start;
            this.start = end;
        }
    }

    /**
     * Constructs a geometry.Line with the specified coordinates for the start and end Points.
     * The function makes the lowest value of x to be the starting point.
     *
     * @param x1 the x-coordinate of the starting geometry.Point
     * @param y1 the y-coordinate of the starting geometry.Point
     * @param x2 the x-coordinate of the ending geometry.Point
     * @param y2 the y-coordinate of the ending geometry.Point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.firstDot = new Point(x1, y1);
        this.secDot = new Point(x2, y2);
        Point start;
        Point end;
        if (x1 < x2) {
            start = new Point(x1, y1);
            end = new Point(x2, y2);
        } else {
            start = new Point(x2, y2);
            end = new Point(x1, y1);
        }
        this.start = start;
        this.end = end;

    }
    /**
     @return the first dot of this object.
     */
    public Point getFirstDot() {
        return firstDot;
    }
    /**
     @return the second dot of this object.
     */
    public Point getSecDot() {
        return secDot;
    }

    /**
     * Calculates the length of the line segment.
     * @return the length of the line segment
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculates the midpoint of the line segment.
     * @return the midpoint of the line segment
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the starting geometry.Point of the line segment.
     * @return the starting geometry.Point of the line segment
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending geometry.Point of the line segment.
     * @return the ending geometry.Point of the line segment
     */
    public Point end() {
        return this.end;
    }

    /**
     * Determines whether this geometry.Line intersects with another geometry.Line.
     * @param other the geometry.Line to test for intersection with this geometry.Line
     * @return true if the Lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (equals(other)) {
            return true;
        }
        double thisM, otherM, thisB, otherB;
        otherM = other.start.incline(other.end);
        thisM = this.start.incline(this.end);
        thisB = -thisM * this.start.getX() + this.start.getY();
        otherB = -otherM * other.start.getX() + other.start.getY();
        if (Double.isInfinite(thisM) || Double.isInfinite(otherM)) {
            // Check if both slopes are undefined, indicating that the lines are vertical
            if (Double.isInfinite(thisM) && Double.isInfinite(otherM)) {
                if (this.start.getX() == other.start.getX()) {
                    // Lines are vertically aligned and may intersect at any point along the shared x-coordinate
                    double thisMaxY = Math.max(this.start.getY(), this.end.getY());
                    double thisMinY = Math.min(this.start.getY(), this.end.getY());
                    double otherMaxY = Math.max(other.start.getY(), other.end.getY());
                    double otherMinY = Math.min(other.start.getY(), other.end.getY());
                    //check if the lines don't touch
                    if (thisMaxY < otherMinY || thisMinY > otherMaxY) {
                        return false;
                    }
                    //check if the lines are one upon the other
                    if ((thisMaxY > otherMinY && thisMaxY < otherMaxY)
                            || (otherMaxY > thisMinY && otherMaxY < thisMaxY)) {
                        return true;
                    }
                    //check if the lines are touching in one point and which one
                    if (thisMaxY == otherMinY) {
                        return true;
                    }
                    if (thisMinY == otherMaxY) {
                        return true;
                    }
                } else {
                    // Lines are parallel and do not intersect
                    return false;
                }
            }
            //Check if the current line slope is undefined
            if (Double.isInfinite(thisM)) {
                //Check if the value of x is in range
                if (other.xInRange(this.start.getX())) {
                    double thisMaxY = Math.max(this.start.getY(), this.end.getY());
                    double thisMinY = Math.min(this.start.getY(), this.end.getY());
                    //yVal is the other line height in the x that this line is infinite.
                    double yVal = (otherM * this.start.getX() + otherB);
                    //Check if the value of y is in range
                    return thisMinY <= yVal && thisMaxY >= yVal;
                } else {
                    return false;
                }
            } else {
                // other line slope is undefined
                if (xInRange(other.start.getX())) {
                    //Check if the value of x is in range
                    double otherMaxY = Math.max(other.start.getY(), other.end.getY());
                    double otherMinY = Math.min(other.start.getY(), other.end.getY());
                    //yVal is this line height in the x that other line is infinite.
                    double yVal = (thisM * other.start.getX() + thisB);
                    //Check if the value of y is in range
                    return otherMinY <= yVal && otherMaxY >= yVal;
                } else {
                    return false;
                }
            }
        }
        // Check if lines are parallel
        if (thisM == otherM) {
            // Check if lines overlap
            if (thisB == otherB) {
                if (this.start.getX() <= other.end.getX() && this.end.getX() >= other.start.getX()) {
                    return true;
                }
                if (other.start.getX() <= this.end.getX() && other.end.getX() >= this.start.getX()) {
                    return true;
                }
            }
            return false;
        }
        //Check if the lines doesn't parallel
        double xCut = (otherB - thisB) / (thisM - otherM);
        return (xInRange(xCut) && other.xInRange(xCut));
    }

    /**
     * Calculates the intersection geometry.Point of this geometry.Line with another geometry.Line, if it exists.
     *
     * @param other the geometry.Line to calculate the intersection geometry.Point with
     * @return the intersection geometry.Point of the Lines, or null if they do not intersect,
     * or they are one upon the other.
     */
    public Point intersectionWith(Line other) {
        if (equals(other)) {
            return null;
        }
        double thisM, otherM, thisB, otherB;
        thisM = this.start.incline(this.end);
        otherM = other.start.incline(other.end);
        thisB = -thisM * this.start.getX() + this.start.getY();
        otherB = -otherM * other.start.getX() + other.start.getY();
        // Check if either slope is undefined (i.e., infinite)
        if (Double.isInfinite(thisM) || Double.isInfinite(otherM)) {
            // Check if both slopes are undefined, indicating that the lines are vertical
            if (Double.isInfinite(thisM) && Double.isInfinite(otherM)) {
                if (this.start.getX() == other.start.getX()) {
                    // Lines are vertically aligned and may intersect at any point along the shared x-coordinate
                    double thisMaxY = Math.max(this.start.getY(), this.end.getY());
                    double thisMinY = Math.min(this.start.getY(), this.end.getY());
                    double otherMaxY = Math.max(other.start.getY(), other.end.getY());
                    double otherMinY = Math.min(other.start.getY(), other.end.getY());
                    //check if the lines don't touch
                    if (thisMaxY < otherMinY || thisMinY > otherMaxY) {
                        return null;
                    }
                    //check if the lines are one upon the other
                    if ((thisMaxY > otherMinY && thisMaxY < otherMaxY)
                            || (otherMaxY > thisMinY && otherMaxY < thisMaxY)) {
                        return null;
                    }
                    //check if the lines are touching in one point and which one
                    if (thisMaxY == otherMinY) {
                        return new Point(this.start.getX(), thisMaxY);
                    }
                    if (thisMinY == otherMaxY) {
                        return new Point(this.start.getX(), thisMinY);
                    }
                } else {
                    // Lines are parallel and do not intersect
                    return null;
                }
            }
            //Check if the current line slope is undefined
            if (Double.isInfinite(thisM)) {
                double thisMaxY = Math.max(this.start.getY(), this.end.getY());
                double thisMinY = Math.min(this.start.getY(), this.end.getY());
                //yVal is the other line height in the x that this line is infinite.
                double yVal = (otherM * this.start.getX() + otherB);
                //Check if the value of y and x is in range
                if (other.xInRange(this.start.getX()) && (thisMinY <= yVal && thisMaxY >= yVal)) {
                    return new Point(this.start.getX(), this.start.getX() * otherM + otherB);
                } else {
                    return null;
                }
            } else {
                // other line slope is undefined
                //Check if the value of x is in range
                double otherMaxY = Math.max(other.start.getY(), other.end.getY());
                double otherMinY = Math.min(other.start.getY(), other.end.getY());
                //yVal is this line height in the x that other line is infinite.
                double yVal = (thisM * other.start.getX() + thisB);
                //Check if the value of y is in range
                if (xInRange(other.start.getX()) && (otherMinY <= yVal && otherMaxY >= yVal)) {
                    return new Point(other.start.getX(), other.start.getX() * thisM + thisB);
                } else {
                    return null;
                }
            }
        }

        // Check if lines are parallel
        if (thisM == otherM) {
            // Check if lines overlap
            if (thisB == otherB) {
                if (this.end.getX() == other.start.getX()) {
                    return this.end;
                }
                if (this.start.getX() == other.end.getX()) {
                    return this.start;
                }
            }
            return null;
        }
        //Check if the lines doesn't parallel
        double xCut = (otherB - thisB) / (thisM - otherM);
        if (xInRange(xCut) && other.xInRange(xCut)) {
            return new Point(xCut, xCut * thisM + thisB);
        }
        return null;
    }

    /**
     * Returns true if this line is equal to the given line, i.e., if they have the same start and end points.
     *
     * @param other the other line to compare with
     * @return true if this line is equal to the given line, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }

    /**
     * Returns true if the given value of x is within the range of the line segment.
     *
     * @param x the value of x to check
     * @return true if the value of x is within the range of the line segment, false otherwise
     */
    public boolean xInRange(double x) {
        double startX = this.start.getX();
        double endX = this.end.getX();
        double threshold = 0.0001;
        return Math.abs(x - startX) <= threshold || Math.abs(x - endX) <= threshold
                || (startX < x && x < endX) || (endX < x && x < startX);
    }

    /**
     Finds the closest intersection point between this line and a given rectangle.
     If there is no intersection point, returns null.
     @param rect the rectangle to check for intersection with
     @return the closest intersection point to the start of this line, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //Used for measuring the distance for ech point
        double topHorizontalLine, bottomHorizontalLine, leftVerticalLine, rightVerticalLine;
        // Intersection points with all the lines
        Point pointTopHorizontalLine, pointBottomHorizontalLine, pointLeftVerticalLine, pointRightVerticalLine;
        pointTopHorizontalLine = intersectionWith(rect.getTopHorizontalLine());
        pointBottomHorizontalLine = intersectionWith(rect.getBottomHorizontalLine());
        pointLeftVerticalLine = intersectionWith(rect.getLeftVerticalLine());
        pointRightVerticalLine = intersectionWith(rect.getRightVerticalLine());
        //Check if there is intersection point what is the distance
        if (pointTopHorizontalLine != null) {
            topHorizontalLine = this.firstDot.distance(pointTopHorizontalLine);
        } else {
            topHorizontalLine = -1;
        }
        if (pointBottomHorizontalLine != null) {
            bottomHorizontalLine = this.firstDot.distance(pointBottomHorizontalLine);
        } else {
            bottomHorizontalLine = -1;
        }
        if (pointLeftVerticalLine != null) {
            leftVerticalLine = this.firstDot.distance(pointLeftVerticalLine);
        } else {
            leftVerticalLine = -1;
        }
        if (pointRightVerticalLine != null) {
            rightVerticalLine = this.firstDot.distance(pointRightVerticalLine);
        } else {
            rightVerticalLine = -1;
        }
        //check what is the smallest distance
        double smallest = findSmallest(topHorizontalLine, bottomHorizontalLine, leftVerticalLine, rightVerticalLine);
        //Compare and return the point with the lower distance
        if (smallest == topHorizontalLine) {
            return pointTopHorizontalLine;
        } else if (smallest == bottomHorizontalLine) {
            return pointBottomHorizontalLine;
        } else if (smallest == leftVerticalLine) {
            return pointLeftVerticalLine;
        } else if (smallest == rightVerticalLine) {
            return pointRightVerticalLine;
        } else {
            return null;
        }
    }
    /**
     Finds the smallest non-negative value from the given set of doubles.
     @param num1 the first double value to compare.
     @param num2 the second double value to compare.
     @param num3 the third double value to compare.
     @param num4 the fourth double value to compare.
     @return the smallest non-negative value from the given set of doubles.
     If there are no non-negative values, returns -1.
     */
    private double findSmallest(Double num1, Double num2, Double num3, Double num4) {
        double smallest = -1;
        if (num1 >= 0) {
            smallest = num1;
        }
        if (num2 >= 0 && (num2 < smallest || smallest == -1)) {
            smallest = num2;
        }
        if (num3 >= 0 && (num3 < smallest || smallest == -1)) {
            smallest = num3;
        }
        if (num4 >= 0 && (num4 < smallest || smallest == -1)) {
            smallest = num4;
        }
        return smallest;
    }
    /**
     Checks if this line is on top of another line.
     Returns true if they intersect and no intersection point is found (meaning they are on top of each other).
     @param other The other line to check against.
     @return True if this line is on top of the other line, false otherwise.
     */
    public boolean onUponTheOther(Line other) {
        if (!isIntersecting(other)) {
            return false;
        }
        if (intersectionWith(other) != null) {
            return false;
        }
        return true;

    }
    /**
     Checks if a given point is inside the line segment.
     @param p the point to check
     @return true if the point is inside the line segment, false otherwise
     */
    public boolean isPointInsideLine(Point p) {
        if (!xInRange(p.getX())) {
            return false;
        }
        double thisM = this.start.incline(this.end);
        double thisB = -thisM * this.start.getX() + this.start.getY();
        if (Double.isInfinite(thisM)) {
            double thisMaxY = Math.max(this.start.getY(), this.end.getY());
            double thisMinY = Math.min(this.start.getY(), this.end.getY());
            return p.getY() >= thisMinY && p.getY() <= thisMaxY;
        }
        double threshold = 0.0001; //accuracy level
        return Math.abs(thisM * p.getX() + thisB - p.getY()) <= threshold;

    }

    /**
     Draws the line on a given DrawSurface.
     @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    /**
     * Notifies the sprite that time has passed.
     * This method is used for updating the state of the sprite according to the game's timer.
     */
    @Override
    public void timePassed() {
        //do nothing
    }


}