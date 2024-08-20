/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import geometry.Ball;
import geometry.Block;
import geometry.HitListener;
/**
 * The ScoreTrackingListener class is responsible for tracking the score when blocks are hit.
 * It implements the HitListener interface.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    private static final int SCORE_FOR_BLOCK_HIT = 5;
    /**
     * Constructs a ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter The counter to track the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Handles the hit event triggered when a block is hit by a ball.
     *
     * @param beingHit The block being hit.
     * @param hitter   The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //we will take care after the 100 points
        this.currentScore.increase(SCORE_FOR_BLOCK_HIT);

    }
}
