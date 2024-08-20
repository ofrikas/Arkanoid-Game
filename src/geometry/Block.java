/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Velocity;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;



/**
 * The geometry.Block class represents a rectangle in 2D space, defined by a geometry.Rectangle object.
 * The block is an object in the game that can be either a border of the board or a brick.
 * The class implements the geometry.Collidable and geometry.
 * Sprite interfaces and provides methods for collision detection and drawing on a DrawSurface object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rec;
    private Color color;
    private final List<HitListener> hitListeners;

    /**
     * Constructor for geometry.Block class.
     *
     * @param rec the rectangle defining the block's shape and position.
     */
    public Block(Rectangle rec) {
        this.rec = rec;
        this.color = Color.green;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Getter for the rectangle defining the block's shape and position.
     *
     * @return the rectangle defining the block's shape and position.
     */
    public Rectangle getRec() {
        return rec;
    }

    /**
     * Gets the collision rectangle of the block.
     *
     * @return the collision rectangle of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    /**
     * Sets the color of the block.
     *
     * @param color the new color of the block.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the new velocity of a ball after hitting the block.
     *
     * @param collisionPoint  the point at which the ball collides with the block.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity of the ball after hitting the block.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        /*Check witch borders are collision and change the velocity accordingly. We add protection to some cases:
          1) If the ball is going up, it can not hit the top horizontal line.
          2) If the ball is going down, it can not hit the bottom horizontal line.
          3) If the ball is going left, it can not hit the left vertical line.
          4) If the ball is going right, it can not hit the right vertical line.
         */
        if (currentVelocity.getDy() > 0 && rec.getTopHorizontalLine().isPointInsideLine(collisionPoint)) {
            v.setDy(-v.getDy());
        }
        if (currentVelocity.getDy() < 0 && rec.getBottomHorizontalLine().isPointInsideLine(collisionPoint)) {
            v.setDy(-v.getDy());
        }
        if (currentVelocity.getDx() > 0 && rec.getLeftVerticalLine().isPointInsideLine(collisionPoint)) {
            v.setDx(-v.getDx());
        }
        if (currentVelocity.getDx() < 0 && rec.getRightVerticalLine().isPointInsideLine(collisionPoint)) {
            v.setDx(-v.getDx());
        }
        notifyHit(hitter);
        return v;
    }

    /**
     * Draws the block on a given surface.
     *
     * @param surface the surface on which to draw the block.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        this.rec.drawOn(surface);

    }

    /**
     * Currently does nothing as blocks do not change over time.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds this block to the game - adds it to both the sprites and the collidables lists of the given game.
     *
     * @param gameLevel the game to add this block to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Removes the collidable and sprite associated with this object from the specified game.
     *
     * @param gameLevel The game from which to remove the collidable and sprite.
     * @return {@code true} if the removal was successful, {@code false} otherwise.
     */
    public boolean removeFromGame(GameLevel gameLevel) {
        return gameLevel.removeCollidable(this) && gameLevel.removeSprite(this);
    }

    /**
     * Adds the specified HitListener to the list of hit listeners.
     *
     * @param hl The HitListener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }


    /**
     * Removes the specified HitListener from the list of hit listeners, if it exists.
     *
     * @param hl The HitListener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
            this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered HitListeners about a hit event with the specified Ball.
     * Makes a copy of the hitListeners list before iterating over them to avoid
     * ConcurrentModificationException if a listener is added or removed during iteration.
     *
     * @param hitter The Ball object that caused the hit event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
