/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.Velocity;

import java.awt.Color;

/**
 * A class representing a paddle in the game.
 */
public class Paddle implements Sprite, Collidable {

    //The number of regions in the paddle for collision detection.
    private static final int REGION_NUMBER = 5;
    //The angle in degrees for region 1 in the paddle.
    private static final double ANGLE1 = 300;
    //The angle in degrees for region 2 in the paddle.
    private static final double ANGLE2 = 330;
    //The angle in degrees for region 4 in the paddle.
    private static final double ANGLE4 = 30;
    //The angle in degrees for region 5 in the paddle.
    private static final double ANGLE5 = 60;
    //The movement speed of the paddle.
    private final int movement;
    //The keyboard sensor used to read user input for moving the paddle.
    private final biuoop.KeyboardSensor keyboard;
    //The color of the paddle.
    private final Color color;
    //The rectangle object representing the paddle.
    private Rectangle rec;

    /**
     * Constructs a new paddle object with the specified keyboard sensor, rectangle, and color.
     *
     * @param keyboard    the keyboard sensor used to read user input for moving the paddle.
     * @param rec         the rectangle object representing the paddle.
     * @param color       the color of the paddle.
     * @param paddleSpeed the speed of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rec, Color color, int paddleSpeed) {
        this.keyboard = keyboard;
        this.rec = rec;
        this.color = color;
        this.movement = paddleSpeed;
    }

    /**
     * @return true if the paddle is currently moving right based on user input, false otherwise.
     */
    public boolean isPaddleGoingRight() {
        return keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && !keyboard.isPressed(KeyboardSensor.LEFT_KEY);
    }

    /**
     * @return true if the paddle is currently moving left based on user input, false otherwise.
     */
    public boolean isPaddleGoingLeft() {
        return keyboard.isPressed(KeyboardSensor.LEFT_KEY) && !keyboard.isPressed(KeyboardSensor.RIGHT_KEY);
    }

    /**
     * @return the rectangle object representing the paddle's position after moving it to the right by one step.
     */
    public Rectangle getFutureRightRectangle() {
        Point curUpperLeft = rec.getUpperLeft();
        double newX = curUpperLeft.getX() + movement;
        if (newX <= 660) {
            return new Rectangle(new Point(newX, curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        } else {
            return new Rectangle(new Point(660, curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * @return the rectangle object representing the paddle's position after moving it to the left by one step.
     */
    public Rectangle getFutureLeftRectangle() {
        Point curUpperLeft = rec.getUpperLeft();
        double newX = curUpperLeft.getX() - movement;
        if (newX >= 40) {
            return new Rectangle(new Point(newX, curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        } else {
            return new Rectangle(new Point(40, curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        Point curUpperLeft = rec.getUpperLeft();
        double newX = curUpperLeft.getX() - movement;
        if (newX >= 40) {
            this.rec = new Rectangle(new Point(newX, curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        } else {
            this.rec = new Rectangle(new Point(40, curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        Point curUpperLeft = rec.getUpperLeft();
        double newX = curUpperLeft.getX() + movement;
        if (newX + this.rec.getWidth() <= 800 - 40) { //width of the screen is 800, and border is 40
            this.rec = new Rectangle(new Point(newX, curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        } else {
            this.rec = new Rectangle(new Point(
                    800 - 40 - this.rec.getWidth(), curUpperLeft.getY()), rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * Move the paddle according to the keyboard input.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle on a given surface.
     *
     * @param d the surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * @return the collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Calculate the new velocity of the ball after it hits the paddle.
     *
     * @param hitter          the ball that hit the paddle.
     * @param collisionPoint  the collision point of the ball with the paddle.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity of the ball after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        /*Check witch borders are collision and change the velocity accordingly. We add protection to some cases:
          1) If the ball is going up, it can not hit the top horizontal line.
          2) If the ball is going down, it can not hit the bottom horizontal line.
          3) If the ball is going left, it can not hit the left vertical line.
          4) If the ball is going right, it can not hit the right vertical line.
         */
        if (currentVelocity.getDy() > 0 && rec.getTopHorizontalLine().isPointInsideLine(collisionPoint)) {
            //Creat the regions according to the size of the paddle.
            Point upperLeft = rec.getUpperLeft();
            double regSize = rec.getTopHorizontalLine().length() / REGION_NUMBER;
            Line reg1 = new Line(upperLeft, new Point(upperLeft.getX() + regSize, upperLeft.getY()));
            Line reg2 = new Line(new Point(upperLeft.getX() + regSize, upperLeft.getY()),
                    new Point(upperLeft.getX() + regSize * 2, upperLeft.getY()));
            Line reg3 = new Line(new Point(upperLeft.getX() + regSize * 2, upperLeft.getY()),
                    new Point(upperLeft.getX() + regSize * 3, upperLeft.getY()));
            Line reg4 = new Line(new Point(upperLeft.getX() + regSize * 3, upperLeft.getY()),
                    new Point(upperLeft.getX() + regSize * 4, upperLeft.getY()));
            Line reg5 = new Line(new Point(upperLeft.getX() + regSize * 4, upperLeft.getY()), rec.getUpperRight());

            double speed = getBaseSpeed(currentVelocity.getDx(), currentVelocity.getDy());
            //Check which region the ball collided with
            if (reg1.isPointInsideLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(ANGLE1, speed);
            } else if (reg2.isPointInsideLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(ANGLE2, speed);
            } else if (reg3.isPointInsideLine(collisionPoint)) {
                v.setDy(-v.getDy());
            } else if (reg4.isPointInsideLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(ANGLE4, speed);
            } else if (reg5.isPointInsideLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(ANGLE5, speed);
            }

        }
        if (currentVelocity.getDx() > 0 && rec.getLeftVerticalLine().isPointInsideLine(collisionPoint)) {
            v.setDx(-v.getDx());
        }
        if (currentVelocity.getDx() < 0 && rec.getRightVerticalLine().isPointInsideLine(collisionPoint)) {
            v.setDx(-v.getDx());
        }
        return v;
    }


    /**
     * This method adds the geometry.Paddle object to the game.
     * It adds the geometry.Paddle as both a geometry.Sprite and a geometry.Collidable object to the given game.
     *
     * @param g The game.Game object to which the geometry.Paddle will be added.
     */
    public void addToGame(GameLevel g) {
        g.getSprites().addSprite(this);
        g.getEnvironment().getListOfCollidable().add(this);
    }

    /**
     * This method calculates the base speed of a ball based on its x and y components of velocity.
     *
     * @param dx The x component of the ball's velocity.
     * @param dy The y component of the ball's velocity.
     * @return The base speed of the ball.
     */
    private double getBaseSpeed(double dx, double dy) {
        return Math.sqrt(dx * dx + dy * dy);
    }
}