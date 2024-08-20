/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Ball;
import geometry.Block;
import geometry.HitListener;
/**
 * The BallRemover class is responsible for removing balls from the game and keeping count of the remaining balls.
 * It implements the HitListener interface.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Constructs a BallRemover with the specified game and remaining balls counter.
     *
     * @param gameLevel           The game from which balls will be removed.
     * @param removedBalls The counter that keeps track of the number of remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }


    /**
     * Handles a hit event on a block.
     * Removes the ball that hit the block from the game, decreases the count of remaining balls by 1,
     * and removes this listener from the block if there are no more remaining balls.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
        if (remainingBalls.isEmpty()) {
            beingHit.removeHitListener(this);
        }
    }
}
