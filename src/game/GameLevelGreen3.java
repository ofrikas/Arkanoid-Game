/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameLevelGreen3 class implements the LevelInformation interface
 * to define the properties of the "Green 3" level in the game.
 */
public class GameLevelGreen3 implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 2;
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
        velList.add(Velocity.fromAngleAndSpeed(-45, 5));
        velList.add(Velocity.fromAngleAndSpeed(45, 5));
        return velList;
    }

    /**
     * Returns the starting point that the balls should be start at in the game.
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
        return 6;
    }

    /**
     * Returns the color of the balls in the level.
     *
     * @return the ball color
     */
    @Override
    public Color ballColor() {
        return Color.black;
    }

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 10;
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
        return "Green 3";
    }

    /**
     * Returns the background list of sprites of the level.
     *
     * @return the background sprite
     */
    @Override
    public List<Sprite> getBackground() {
        return null;
    }

    /**
     * Returns the background color of the level.
     *
     * @return the background color
     */
    @Override
    public Color backgroundColor() {
        return Color.pink;
    }

    /**
     * Returns the blocks that make up this level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    @Override
    public List<Block> blocks() {
        int rectangleWidth = 50, rectangleHeight = 35, firstLocXBlock = 160,
                firstLocYBlock = 130, rowsOfBlocks = 6, blocksInUpperLine = 12;
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < rowsOfBlocks; i++) {
            for (int j = 0; j < blocksInUpperLine - i; j++) {
                //Creat a block in the correct place.
                Block block = new Block(new Rectangle(new Point(
                        firstLocXBlock + i * rectangleWidth + j * rectangleWidth,
                        firstLocYBlock + i * rectangleHeight), rectangleWidth, rectangleHeight));
                //Set row color in ech block.
                if (i == 0) {
                    block.setColor(new Color(0, 0, 255)); // pure blue
                } else if (i == 1) {
                    block.setColor(new Color(30, 144, 255)); // dodger blue
                } else if (i == 2) {
                    block.setColor(new Color(135, 206, 250)); // light sky blue
                } else if (i == 3) {
                    block.setColor(new Color(70, 130, 180)); // // steel blue
                } else if (i == 4) {
                    block.setColor(new Color(0, 191, 255)); // // deep sky blue
                } else {
                    block.setColor(new Color(100, 149, 237)); // cornflower blue
                }
                blocks.add(block);

            }
        }
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
        return 57;
    }
}
