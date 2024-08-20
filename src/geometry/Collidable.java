/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

import biuoop.DrawSurface;
import game.Velocity;

/**
 * The geometry.Collidable interface represents an object in the game that can collide with other objects.
 * It defines methods for retrieving the collision shape of the object and handling collisions.
 */

public interface Collidable {
    /**
     * Returns the "collision shape" of the object, represented as a geometry.Rectangle.
     *
     * @return the geometry.Rectangle representing the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that it has collided with another object at the given collision point and velocity.
     * The method calculates the new velocity of the object after the hit, based on the force the other object
     * inflicted on it and the location of the hit.
     * For example, hit in the left line of the rectangle will change the direction of the collided object to the left.
     *
     * @param hitter          - the ball that hit the object
     * @param collisionPoint  - the point where the collision occurred
     * @param currentVelocity - the current velocity of the object
     * @return the new velocity of the object after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


    /**
     * Draws the object on the given DrawSurface.
     *
     * @param surface - the DrawSurface to draw the object on
     */
    void drawOn(DrawSurface surface);
}