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

/**
 * BlockRemover class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    // reference to game.
    private GameLevel game;
    // number of blocks.
    private Counter remainingBlocks;

    /**
     * Constructor for listeners.BlockRemover.
     * @param game - get game.
     * @param removedBlocks - the count blocks that remain.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**.
     * Blocks that are hit should be removed from the game.
     * removed this listener from the block that is being removed from
     * the game.
     * @param beingHit - the block that being hit.
     * @param hitter - the hiiter ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        // num of blocks --
        this.remainingBlocks.decrease(1);
    }
}
