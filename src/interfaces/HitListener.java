/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package interfaces;
import game.Ball;
import game.Block;

/**.
 * HisListener class for Observer type.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the game.Ball that's doing the hitting.
     * @param beingHit - the block that being hit.
     * @param hitter - the ball that hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
