/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;
/**
 * The HitListener interface represents an object that listens to hit events.
 * Objects implementing this interface can be registered as listeners to receive notifications
 * when a specific object is hit by a ball.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
