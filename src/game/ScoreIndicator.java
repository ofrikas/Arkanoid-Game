/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;
import geometry.Sprite;
import java.awt.Color;



/**
 * The ScoreIndicator class is responsible for displaying the current score at the top of the screen.
 */
public class ScoreIndicator implements Sprite {

    private final int textX, textY, textSize;
    private final Counter currentScore;
    /**
     * Constructs a ScoreIndicator with the specified current score, text position, and text size.
     *
     * @param currentScore The counter representing the current score.
     * @param textX        The x-coordinate of the text position.
     * @param textY        The y-coordinate of the text position.
     * @param textSize     The size of the text.
     */
    public ScoreIndicator(Counter currentScore, int textX, int textY, int textSize) {
        this.currentScore = currentScore;
        this.textX = textX;
        this.textY = textY;
        this.textSize = textSize;
    }

    /**
     * Draws the score on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the score on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color textColor = Color.BLACK;
        d.setColor(textColor);
        d.drawText(textX, textY, "Score: " + currentScore.getValue(), textSize);
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
     * Adds the ScoreIndicator to the given game.
     *
     * @param gameLevel The game to add the ScoreIndicator to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getSprites().addSprite(this);
    }
}
