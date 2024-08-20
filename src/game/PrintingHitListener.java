/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Ball;
import geometry.Block;
import geometry.HitListener;

/**
 * A HitListener implementation that prints a message when a Block is hit.
 */
public class PrintingHitListener implements HitListener {

    /**
     * Invoked when a Block is hit.
     * Prints a message indicating that a Block was hit.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball object that hit the Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}