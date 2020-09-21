/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package levels;

import game.Block;
import game.Velocity;
import interfaces.Sprite;
import java.util.List;

/**
 * The class interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**.
     * the number of balls in the level.
     * @return - number of balls.
     */
    int numberOfBalls();

    /**.
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return - list which has initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**.
     * The paddle speed.
     * @return - paddle speed.
     */
    int paddleSpeed();

    /**
     * the paddle Width.
     * @return - paddle Width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return - string of level name.
     */
    String levelName();

    /**.
     * background of the level.
     * @return - a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * Function that will return The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return - list of blocks level.
     */
    List<Block> blocks();

    /**.
     * Number of blocks that should be removed before the level is considered to
     * been "cleared". This number should be <= blocks.size();
     * @return - number Of Blocks To Remove.
     */
    int numberOfBlocksToRemove();
}
