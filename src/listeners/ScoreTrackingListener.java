/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package listeners;

import game.Ball;
import game.Block;
import game.Counter;
import interfaces.HitListener;

/**.
 * Listener for tracking the player score.
 */
public class ScoreTrackingListener implements HitListener {
    // the current score of player.
    private Counter currentScore;

    /**.
     * Constructor for listeners.ScoreTrackingListener.
     * @param scoreCounter - get current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * When get hit event increase the score in 5.
     * @param beingHit - block that being hit.
     * @param hitter - ball that hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
