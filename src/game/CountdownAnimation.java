/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The CountdownAnimation class implements the Animation interface and represents an animation
 * that displays a countdown from a specified number on the screen.
 */
public class CountdownAnimation implements Animation {
    private final int countFrom;
    private final SpriteCollection sprites;
    private final long startTime;
    private final double displayDuration; // Duration for each number's display
    private final Color backgroundColor;
    private int currentNumber; // The current number in the countdown

    /**
     * Constructs a CountdownAnimation object.
     *
     * @param numOfSeconds The total duration of the countdown in seconds
     * @param countFrom    The number to count down from
     * @param sprites      Collection of sprites to display alongside the countdown
     * @param backgroundC  The background color of the animation
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection sprites, Color backgroundC) {
        this.countFrom = countFrom;
        this.sprites = sprites;
        this.startTime = System.currentTimeMillis();
        this.currentNumber = countFrom;
        this.displayDuration = numOfSeconds / (double) countFrom;
        this.backgroundColor = backgroundC;
    }

    /**
     * Performs one frame of the countdown animation.
     *
     * @param d The draw surface to draw the animation on
     */
    public void doOneFrame(DrawSurface d) {
        // Display the game screen
        d.setColor(this.backgroundColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        this.sprites.drawAllOn(d);

        // Calculate the elapsed time
        double elapsedTime = (System.currentTimeMillis() - this.startTime) / 1000.0;

        // Calculate the current countdown number based on the elapsed time
        int newNumber = countFrom - (int) (elapsedTime / displayDuration);
        if (newNumber < currentNumber) {
            currentNumber = newNumber;
        }

        // Draw the current countdown number on the screen
        String countdownText = Integer.toString(currentNumber);
        int textX = d.getWidth() / 2 - 50;
        int textY = d.getHeight() / 2;
        drawCountdownText(d, countdownText, textX, textY);
    }

    /**
     * Determines whether the animation should stop or continue.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        // Stop the animation when the countdown completes
        return currentNumber <= 0;
    }

    private void drawCountdownText(DrawSurface d, String text, int x, int y) {
        // Customize the appearance of the countdown text
        d.setColor(Color.WHITE);
        d.drawText(x, y, text, 200);
        d.setColor(Color.BLACK);
        d.drawText(x - 2, y - 2, text, 200);
    }
}






