/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Block;
import geometry.Point;
import geometry.Sprite;
import java.awt.Color;
import java.util.List;

/**
 * The LevelInformation interface represents information about a game level in a block-breaking game.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns the initial velocities of the balls in the level.
     * Note that the size of the returned list should be equal to the number of balls.
     *
     * @return a list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the starting point that the balls should be start at in the game.
     *
     * @return a point of start for the balls
     */
    Point ballsStartingPoint();

    /**
     * Returns the radius of the balls in the level.
     *
     * @return the ball radius
     */

    int ballRadius();

    /**
     * Returns the color of the balls in the level.
     *
     * @return the ball color
     */
    Color ballColor();

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the length of the paddle in the level.
     *
     * @return the paddle length
     */
    int paddleLength();

    /**
     * Returns the upper left point of the paddle in the level.
     *
     * @return the paddle upper left point
     */
    Point paddleUpperLeftPoint();

    /**
     * Returns the color of the paddle in the level.
     *
     * @return the color
     */
    Color paddleColor();

    /**
     * Returns the name of the level.
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background list of sprites of the level.
     *
     * @return the background sprite
     */
    List<Sprite> getBackground();

    /**
     * Returns the background color of the level.
     *
     * @return the background color
     */
    Color backgroundColor();

    /**
     * Returns the blocks that make up this level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be less than or equal to the total number of blocks in the level.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}

