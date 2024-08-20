/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Collidable;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The game.GameEnvironment class represents the environment in which the game takes place.
 * It contains a list of collidable objects and provides methods for adding new collidables
 * and finding the closest collision of an object with the collidables in the environment.
 */
public class GameEnvironment {

    private final List<Collidable> lc; //List of collidables in the game environment.

    /**
     * Constructs a new game.GameEnvironment object with an empty list of collidables.
     */
    public GameEnvironment() {
        lc = new ArrayList<>();
    }

    /**
     * Returns the list of collidables in the game environment.
     *
     * @return the list of collidables
     */
    public List<Collidable> getListOfCollidable() {
        return lc;
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        lc.add(c);
    }

    /**
     * Finds the closest collision of an object moving along the given trajectory with any of the collidables
     * in the game environment. If there is no collision, returns null.
     *
     * @param trajectoryLine the line representing the trajectory of the moving object
     * @return the game.CollisionInfo object representing the closest collision, or null if there is no collision
     */
    public CollisionInfo getClosestCollision(Line trajectoryLine) {
        double distFromCollisionPoint = -1; // -1 indicate there is no object to collide with yet.
        CollisionInfo colObject = null;
        double smallestCenterDistanceRec = -1;
        for (Collidable c : lc) {
            java.util.List<Point> collidePoints = c.getCollisionRectangle().intersectionPoints(trajectoryLine);
            if (!collidePoints.isEmpty()) {
                //get(0)the closest one
                double currentDistance = collidePoints.get(0).distance(trajectoryLine.getFirstDot());
                if (distFromCollisionPoint == -1 || distFromCollisionPoint > currentDistance) {
                    distFromCollisionPoint = currentDistance;
                    colObject = new CollisionInfo(collidePoints.get(0), c);
                    smallestCenterDistanceRec = c.getCollisionRectangle()
                            .getCenter().distance(trajectoryLine.getFirstDot());
                } else if (distFromCollisionPoint == currentDistance) { // There are 2 dots of the same location
                    //We want to return the point connected to the most cloth rectangle. We check it with the distance
                    //from the center of the rectangle
                    double centerDistanceRecCurrent = c.getCollisionRectangle()
                            .getCenter().distance(trajectoryLine.getFirstDot());
                    if (centerDistanceRecCurrent < smallestCenterDistanceRec) { //If we found closet rectangle switch.
                        colObject = new CollisionInfo(collidePoints.get(0), c);
                    }

                }
            }
        }
        return colObject;
    }

}