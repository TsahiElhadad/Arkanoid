/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package listeners;

import game.Ball;
import game.Block;
import game.Counter;
import game.GameLevel;
import interfaces.HitListener;

/**.
 * Class will be in charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    // reference to game.
    private GameLevel game;
    // number of balls.
    private Counter remainingBalls;

    /**
     * Constructor for listeners.BallRemover, get game and number of balls in the game.
     * @param game - game.
     * @param remainingBalls - balls that remain.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**.
     * Blocks that are hit should be removed from the game.
     * removed this listener from the block that is being removed from
     * the game.
     * @param beingHit - the block that being hit.
     * @param hitter - the hitter ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        // num of balls --
        this.remainingBalls.decrease(1);
    }
}
