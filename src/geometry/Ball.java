/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;


import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import game.Velocity;




/**
 * A class representing a ball.
 */
public class Ball implements Sprite {
    // the radius of the ball
    private final int r;
    // the color of the ball
    private final java.awt.Color color;
    private static final double MAX_X = 760, MAX_Y = 560, MIN_X = 40;
    private final GameEnvironment gE; //reference to a game.GameEnvironment
    private final Paddle paddle;
    // the center point of the ball
    private Point center;
    private Velocity velocity;  // the velocity of the ball


    /**
     * Constructor.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param gE     the game environment of the ball
     * @param paddle the paddle object of the game
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gE, Paddle paddle) {
        this.center = center;
        this.r = r;
        this.color = color;
        // Creat a velocity of zero to prevent crashing if velocity will not be set
        this.velocity = new Velocity(0, 0);
        this.gE = gE;
        this.paddle = paddle;
    }

    /**
     * Returns the x-coordinate of the center point of the ball.
     *
     * @return the x-coordinate of the center point of the ball
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Returns the y-coordinate of the center point of the ball.
     *
     * @return the y-coordinate of the center point of the ball
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Draws the ball on the given draw surface.
     *
     * @param surface the draw surface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    @Override
    public void timePassed() {
        moveOneStep();

    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * Moves the ball one step according to its current velocity.
     * Calculates the next position of the ball,
     * checks if moving on that trajectory will hit anything using the game environment,
     * and if so, moves the ball to the "almost" hit point and notifies the hit object of the collision using its hit()
     * method.
     * If there is no hit, moves the ball to the end of the trajectory according to its velocity.
     */
    public void moveOneStep() {
        double x = this.center.getX();
        double y = this.center.getY();
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();

        // Calculate the position of the ball after one step, including the radius according to the velocity
        double nextX, nextY;
        if (dx < 0) { //The ball going to the left
            nextX = x + dx - r;
        } else {
            nextX = x + dx + r;
        }
        //The ball going up
        if (dy < 0) {
            nextY = y + dy - r;
        } else {
            nextY = y + dy + r;
        }


        //compute the ball trajectory the trajectory is "how the ball will move"
        Line trajectory = new Line(this.center, new Point(nextX, nextY));

        //Check (using the game environment) if moving on this trajectory will hit anything.
        CollisionInfo ci = this.gE.getClosestCollision(trajectory);

        // If no, then move the ball to the end of the trajectory.
        if (ci == null) {
            // Move the ball according to its velocity
            this.center = this.velocity.applyToPoint(this.center);
        } else {
            //Otherwise there is a hit: move the ball to "almost") the hit point, but just slightly before it.
            Point collisionPoint;
            //Check if we will collide with the paddle
            if (ci.collisionObject().getCollisionRectangle().isEqual(this.paddle.getCollisionRectangle())) {
                if (paddle.isPaddleGoingRight()) {
                    Rectangle futurePaddle = this.paddle.getFutureRightRectangle();
                    java.util.List<Point> collidePoints = futurePaddle.intersectionPoints(trajectory);
                    //The ball is already inside put it by the side.
                    if (futurePaddle.isPointInside(this.center)) {
                        collisionPoint = new Point(futurePaddle.getUpperRight().getX(), center.getY() - 2 * r);
                    } else if (collidePoints.isEmpty()) { //Won't hit the future paddle.
                        collisionPoint = ci.collisionPoint();
                    } else {
                        collisionPoint = collidePoints.get(0);
                    }
                } else if ((this.paddle.isPaddleGoingLeft())) { //The same just for the left
                    Rectangle futurePaddle = this.paddle.getFutureLeftRectangle();
                    java.util.List<Point> collidePoints = futurePaddle.intersectionPoints(trajectory);
                    //The ball is already inside put it by the side.
                    if (futurePaddle.isPointInside(this.center)) {
                        collisionPoint = new Point(futurePaddle.getUpperLeft().getX(), center.getY() - 2 * r);
                    } else if (collidePoints.isEmpty()) { //Won't hit the future paddle.
                        collisionPoint = ci.collisionPoint();
                    } else {
                        collisionPoint = collidePoints.get(0);
                    }
                } else { //paddle isn't on a move
                    collisionPoint = ci.collisionPoint();
                }
                //We don't hit the paddle, get the collisionInfo
            } else {
                collisionPoint = ci.collisionPoint();
            }
            double newX = collisionPoint.getX(), newY = collisionPoint.getY();
            //The ball going to the left
            if (dx < 0) {
                newX += r;
            } else {
                newX -= r;
            }
            if (dy < 0) { //The ball going up
                newY += r;
            } else {
                newY -= r;
            }
            //Safety of the bord comes first, making sure that the ball stay on the bord.
            if (newX + r >= MAX_X) {
                newX = MAX_X - r;
            }
            if (newX - r <= MIN_X) {
                newX = MIN_X + r;
            }
            if (newY + r >= MAX_Y) {
                newY = MAX_Y - r;
            }
            this.center = new Point(newX, newY);
            //We used velocity to declare the "almost" term.
            // the ball can go up, down, left or right on its way to the hitting point.

            //notify the hit object (using its hit() method) that a collision occurred.
            Velocity newV = ci.collisionObject().hit(this, collisionPoint, this.velocity);
            this.setVelocity(newV);
        }
    }

    /**
     * Add this ball to the game by adding it to the sprites list of the game.
     *
     * @param gameLevel the game to add the ball to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getSprites().addSprite(this);

    }

    /**
     * Removes the sprite from the specified game.
     *
     * @param gameLevel The game from which to remove the sprite.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }


}