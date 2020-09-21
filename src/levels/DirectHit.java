/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package levels;

import game.Block;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent level 1 of the game level.
 */
public class DirectHit implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> listBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> listOfBlocks;
    private int numberOfBlocksToRemove;
    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 600;
    private static final int WIDTH_RECS_BORDERS = 20;

    /**.
     * Constructor for DirectHit class, initiate the properties of level 1.
     */
    public DirectHit() {
        this.numberOfBalls = 1;
        this.listBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = 6;
        this.paddleWidth = 80;
        this.levelName = "DirectHit";
        this.background = new DirecteHitBackground();
        this.listOfBlocks = new ArrayList<Block>();
        this.numberOfBlocksToRemove = 1;
    }

    /**.
     * the number of balls in the level is 1;
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
        Velocity vel = Velocity.fromAngleAndSpeed(0, 8);
        this.listBallVelocities.add(vel);
        return this.listBallVelocities;
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
     * @return - string of level name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**.
     * background of the level.
     * @return - a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Function that will return The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return - list of blocks level.
     */
    @Override
    public List<Block> blocks() {
        Point upperLeftP = new Point((WIDTH_SCREEN / 2) - 15, 155);
        Block block1 = new Block(new Rectangle(upperLeftP, 30, 30), Color.RED);
        this.listOfBlocks.add(block1);
        return this.listOfBlocks;
    }

    /**
     * Number of blocks that should be removed before the level is considered to
     * been "cleared". This number should be <= blocks.size();
     * @return - number Of Blocks To Remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
