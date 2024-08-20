/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A decorator class that adds "waiting-for-key-press" behavior to an existing animation.
 * The animation will continue running until a specific key is pressed.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;
    private boolean isKeyPressed;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation with the specified parameters.
     *
     * @param keyboardSensor The keyboard sensor used to detect key presses.
     * @param key            The key that triggers the animation to stop.
     * @param animation      The animation to decorate.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
        this.isKeyPressed = false;
        //fixing the bug of the key is already press in advanced
        this.isAlreadyPressed = true;

    }

    /**
     * Performs one frame of the animation on the given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the animation frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Run the animation screen as it should
        animation.doOneFrame(d);
        // Check if the key is pressed and not in advanced
        if (keyboardSensor.isPressed(key)) {
            if (this.isAlreadyPressed) {
                this.isAlreadyPressed = false;
            } else {
                this.isKeyPressed = true;
            }
        }
    }

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.isKeyPressed;
    }
}
