/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;

/**
 * The PauseScreen class implements the Animation interface to create a pause screen animation.
 * It displays the "paused -- press space to continue" message on the screen.
 */
public class PauseScreen implements Animation {

    /**
     * Constructs a PauseScreen object.
     * Initializes the 'stop' flag to false, indicating that the animation should continue.
     */
    public PauseScreen() {
    }

    /**
     * Performs one frame of the pause screen animation.
     * Draws the "paused -- press space to continue" message on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the animation frame
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Determines whether the animation should stop.
     * Since it is a pause screen, the animation should never stop.
     *
     * @return always returns false to indicate that the animation should continue
     */
    public boolean shouldStop() {
        return false;
    }
}

