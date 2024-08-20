/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

import biuoop.DrawSurface;

/**
 * The geometry.Sprite interface represents an object that can be drawn on a DrawSurface and can respond to time
 * passing in the game.
 * Implementing classes must implement the methods defined in this interface.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method is used for updating the state of the sprite according to the game's timer.
     */
    void timePassed();
}