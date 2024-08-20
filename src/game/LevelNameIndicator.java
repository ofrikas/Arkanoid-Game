/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;
import geometry.Sprite;
import java.awt.Color;


/**
 * The LevelNameIndicator class is responsible for displaying the current level name at the top of the screen.
 */
public class LevelNameIndicator implements Sprite {

    private final int textX, textY, textSize;
    private final String levelName;

    /**
     * Constructs a ScoreIndicator with the specified current score, text position, and text size.
     *
     * @param levelName The String representing the name.
     * @param textX     The x-coordinate of the text position.
     * @param textY     The y-coordinate of the text position.
     * @param textSize  The size of the text.
     */
    public LevelNameIndicator(String levelName, int textX, int textY, int textSize) {
        this.levelName = levelName;
        this.textX = textX;
        this.textY = textY;
        this.textSize = textSize;
    }

    /**
     * Draws the level name on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the score on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color textColor = Color.BLACK;
        d.setColor(textColor);
        d.drawText(textX, textY, "Level Name: " + this.levelName, textSize);
    }

    /**
     * Notifies the sprite that time has passed.
     * This method is used for updating the state of the sprite according to the game's timer.
     */
    @Override
    public void timePassed() {
        // Don't have a use yet
    }

    /**
     * Adds the LevelNameIndicator to the given game.
     *
     * @param gameLevel The game to add the ScoreIndicator to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getSprites().addSprite(this);
    }
}



