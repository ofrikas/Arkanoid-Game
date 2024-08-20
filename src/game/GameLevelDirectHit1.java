/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Block;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameLevelDirectHit1 class implements the LevelInformation interface
 * to define the properties of the "Direct Hit" level in the game.
 */
public class GameLevelDirectHit1 implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Returns the initial velocities of the balls in the level.
     * Note that the size of the returned list should be equal to the number of balls.
     *
     * @return a list of initial ball velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velList = new ArrayList<>();
        velList.add(Velocity.fromAngleAndSpeed(0, 5));
        return velList;
    }

    /**
     * .
     * Returns the starting point that the balls should be start at in the game
     *
     * @return a point of start for the balls
     */
    @Override
    public Point ballsStartingPoint() {
        return new Point(400, 510);
    }

    /**
     * Returns the radius of the balls in the level.
     *
     * @return the ball radius
     */
    @Override
    public int ballRadius() {
        return 5;
    }

    /**
     * Returns the color of the balls in the level.
     *
     * @return the ball color
     */
    @Override
    public Color ballColor() {
        return Color.WHITE;
    }

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 15;
    }

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * Returns the length of the paddle in the level.
     *
     * @return the paddle length
     */
    @Override
    public int paddleLength() {
        return 35;
    }

    /**
     * Returns the upper left point of the paddle in the level.
     *
     * @return the paddle upper left point
     */
    @Override
    public Point paddleUpperLeftPoint() {
        return new Point(350, 525);
    }

    /**
     * Returns the color of the paddle in the level.
     *
     * @return the color
     */
    @Override
    public Color paddleColor() {
        return Color.green;
    }

    /**
     * Returns the name of the level.
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns the background list of sprites of the level.
     *
     * @return the background sprite
     */
    @Override
    public List<Sprite> getBackground() {
        List<Sprite> sprites = new ArrayList<>();
        //first arrow
        sprites.add(new Line(new Point(360, 120), new Point(100, 120)));
        sprites.add(new Line(new Point(360, 120), new Point(330, 90)));
        sprites.add(new Line(new Point(360, 120), new Point(330, 150)));
        //second arrow
        sprites.add(new Line(new Point(440, 120), new Point(700, 120)));
        sprites.add(new Line(new Point(440, 120), new Point(470, 90)));
        sprites.add(new Line(new Point(440, 120), new Point(470, 150)));
        //third arrow
        sprites.add(new Line(new Point(400, 180), new Point(400, 500)));
        sprites.add(new Line(new Point(400, 180), new Point(370, 210)));
        sprites.add(new Line(new Point(400, 180), new Point(430, 210)));

        return sprites;

    }

    /**
     * Returns the background color of the level.
     *
     * @return the background color
     */
    @Override
    public Color backgroundColor() {
        return Color.CYAN;
    }

    /**
     * Returns the blocks that make up this level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(380, 100), 40, 40));
        block.setColor(Color.blue);
        blocks.add(block);
        return blocks;
    }

    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be less than or equal to the total number of blocks in the level.
     *
     * @return the number of blocks to remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
