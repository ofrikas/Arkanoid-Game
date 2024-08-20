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
 * The GameLevelWideEasy2 class implements the LevelInformation interface
 * to define the properties of the "Wide Easy 2" level in the game.
 */
public class GameLevelWideEasy2 implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 10;
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
        velList.add(Velocity.fromAngleAndSpeed(-36, 5));
        velList.add(Velocity.fromAngleAndSpeed(-27, 5));
        velList.add(Velocity.fromAngleAndSpeed(-18, 5));
        velList.add(Velocity.fromAngleAndSpeed(-9, 5));
        velList.add(Velocity.fromAngleAndSpeed(9, 5));
        velList.add(Velocity.fromAngleAndSpeed(18, 5));
        velList.add(Velocity.fromAngleAndSpeed(27, 5));
        velList.add(Velocity.fromAngleAndSpeed(36, 5));
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
        return 3;
    }

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 600;
    }

    /**
     * Returns the length of the paddle in the level.
     *
     * @return the paddle length
     */
    @Override
    public int paddleLength() {
        return 20;
    }

    /**
     * Returns the upper left point of the paddle in the level.
     *
     * @return the paddle upper left point
     */
    @Override
    public Point paddleUpperLeftPoint() {
        return new Point(100, 525);
    }

    /**
     * Returns the color of the paddle in the level.
     *
     * @return the color
     */
    @Override
    public Color paddleColor() {
        return Color.orange;
    }

    /**
     * Returns the name of the level.
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns the background list of sprites of the level.
     *
     * @return the background sprite
     */
    @Override
    public List<Sprite> getBackground() {
        List<Sprite> sprites = new ArrayList<>();
        // Add the lines to form the shape of a man
        sprites.add(new Line(new Point(400, 175), new Point(400, 400)));  // Body
        sprites.add(new Line(new Point(400, 275), new Point(350, 300)));  // Left arm
        sprites.add(new Line(new Point(400, 275), new Point(450, 300)));  // Right arm
        sprites.add(new Line(new Point(400, 400), new Point(350, 500)));  // Left leg
        sprites.add(new Line(new Point(400, 400), new Point(450, 500)));  // Right leg
        // Head
        sprites.add(new Line(new Point(375, 175), new Point(425, 175))); //down
        sprites.add(new Line(new Point(375, 175), new Point(375, 135))); //left
        sprites.add(new Line(new Point(425, 175), new Point(425, 135))); //right
        sprites.add(new Line(new Point(375, 135), new Point(425, 135))); //up


        return sprites;
    }


    /**
     * Returns the background color of the level.
     *
     * @return the background color
     */
    @Override
    public Color backgroundColor() {
        return Color.PINK;
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
        for (int i = 0; i < 15; i++) {
            //Creat a block in the correct place.
            Block block = new Block(new Rectangle(new Point(40 + i * 48, 200),
                    48, 35));
            //Set row color in ech block.
            if (i < 2) {
                block.setColor(Color.red);
            } else if (i < 4) {
                block.setColor(Color.ORANGE);
            } else if (i < 6) {
                block.setColor(Color.YELLOW);
            } else if (i < 9) {
                block.setColor(Color.GREEN);
            } else if (i < 11) {
                block.setColor(Color.BLUE);
            } else if (i < 13) {
                block.setColor(Color.PINK);
            } else {
                block.setColor(Color.CYAN);
            }
            blocks.add(block);

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
        return 15;
    }
}
