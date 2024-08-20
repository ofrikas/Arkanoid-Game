/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * The AnimationRunner class is responsible for running an Animation object and displaying it on a GUI.
 * It handles the animation loop and timing of each frame.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    /**
     * Constructs an AnimationRunner.
     *
     * @param gui             the GUI object to display the animation
     * @param framesPerSecond the desired number of frames per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }
    /**
     * Runs the animation.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        // Set the duration for each frame to approximately 16 milliseconds (60 frames per second)
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
