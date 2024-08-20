/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;
/**
 The Animation interface represents an animated object that can be drawn on a surface.
 */
public interface Animation {
        /**
         Performs one frame of the animation on the given DrawSurface.
         @param d The DrawSurface on which to draw the animation frame.
         */
    void doOneFrame(DrawSurface d);
        /**
         Checks if the animation should stop.
         @return true if the animation should stop, false otherwise.
         */
    boolean shouldStop();
}