/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;

/**
 * The winning screen that displays the final score and a message based on the game outcome.
 * Pressing the space key terminates the program.
 */
public class GameOverAnimation implements Animation {
    private final int score;

    /**
     * Constructs a WinningScreen instance.
     *
     * @param score the final score
     */
    public GameOverAnimation(int score) {
        this.score = score;
    }

    /**
     * Performs one frame of the animation.
     *
     * @param d the draw surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        // Draw the winning/losing message and the final score
        d.drawText(100, d.getHeight() / 2, "Game Over.", 32);
        d.drawText(150, d.getHeight() / 2 + 50, "Your score is: " + score, 32);
        d.drawText(200, d.getHeight() / 2 + 100, "press end to exit", 20);
    }

    /**
     * Determines if the animation should stop.
     *
     * @return {@code true} if the space key is pressed, {@code false} otherwise
     */
    public boolean shouldStop() {
        return false;
    }
}
