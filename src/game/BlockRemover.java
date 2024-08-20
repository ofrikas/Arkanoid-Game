/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Ball;
import geometry.Block;
import geometry.HitListener;

/**
 * A BlockRemover is responsible for removing blocks from the game and keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;
    /**
     * Constructs a BlockRemover with the specified game and counter.
     *
     * @param gameLevel           The game from which blocks will be removed.
     * @param removedBlocks The counter that keeps track of the number of blocks remaining.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Handles a hit event on a block.
     * Removes the block from the game, removes this listener from the block,
     * and decreases the count of remaining blocks by 1.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
