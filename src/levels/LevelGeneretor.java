package levels;

import game.Block;
import game.Velocity;
import interfaces.Sprite;

import java.util.List;

public class LevelGeneretor implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**.
     * @param numberOfBalls
     * @param BallsVelocitiesList
     * @param paddleSpeed
     * @param paddleWidth
     * @param levelName
     * @param background
     * @param numberOfBlocksToRemove
     */
    public LevelGeneretor(int numberOfBalls, List<Velocity> BallsVelocitiesList,
                          int paddleSpeed, int paddleWidth, String levelName, Sprite background,
                          int numberOfBlocksToRemove) {
        this.numberOfBalls = numberOfBalls;
        this.initialBallVelocities = BallsVelocitiesList;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
    }

    /**.
     * the number of balls in the level.
     * @return - number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**.
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return - list which has initial velocity of each ball.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return initialBallVelocities;
    }

    /**.
     * The paddle speed.
     * @return - paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * the paddle Width.
     * @return - paddle Width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return - string of level name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * .
     * background of the level.
     *
     * @return - a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**.
     * Function that will return The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return - list of blocks level.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**.
     * Number of blocks that should be removed before the level is considered to
     * been "cleared". This number should be <= blocks.size();
     * @return - number Of Blocks To Remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
