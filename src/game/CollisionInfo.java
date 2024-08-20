/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Collidable;
import geometry.Point;

/**
 game.CollisionInfo holds information about a collision between a collidable object and a point in space.
 It contains the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     Constructor for game.CollisionInfo.
     @param collisionPoint the point at which the collision occurs.
     @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     Returns the point at which the collision occurs.
     @return the collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     Returns the collidable object involved in the collision.
     @return the collidable object.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}