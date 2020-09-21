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
import java.util.Random;

/**.
 * Class that represent level 3, name Wild easy.
 */
public class WideEasy implements LevelInformation {
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
    // block width and height for the blocks inside the screen.
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;

    /**.
     * Constructor for Wild easy class, initiate the properties of level 3.
     */
    public WideEasy() {
        this.numberOfBalls = 11;
        this.listBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = 2;
        this.paddleWidth = 550;
        this.levelName = "Wild easy";
        this.background = new WildEasyBackground();
        this.listOfBlocks = new ArrayList<Block>();
        this.numberOfBlocksToRemove = 15;
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
        for (int i = 0; i < this.numberOfBalls; i++) {
            Velocity vel = getRandVel();
            this.listBallVelocities.add(vel);
        }
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
        Point upperLeft = new Point(WIDTH_RECS_BORDERS + 5, (HEIGHT_SCREEN / 3) + BLOCK_WIDTH);
        for (int i = 0; i < this.numberOfBlocksToRemove; i++) {
            // switch for each line the accurate color.
            Color color = Color.ORANGE;
            switch (i) {
                case (0):
                case (1):
                    color = Color.RED;
                    break;
                case (2):
                case (3):
                    color = Color.ORANGE;
                    break;
                case (4):
                case (5):
                    color = Color.YELLOW;
                    break;
                case (6):
                case (7):
                case (8):
                    color = Color.GREEN;
                    break;
                case (9):
                case (10):
                    color = Color.BLUE;
                    break;
                case (11):
                case (12):
                    color = Color.PINK;
                    break;
                case (13):
                case (14):
                    color = Color.CYAN;
                    break;
                default:
            }
            Block block = new Block(new Rectangle(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT),
                    color);
            this.listOfBlocks.add(block);
            // increase the x value for the next point.
            upperLeft = new Point(upperLeft.getX() + BLOCK_WIDTH, upperLeft.getY());
        }
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

    /**
     * Function that return random game.Velocity for making ball.
     * @return - random game.Velocity.
     */
    private Velocity getRandVel() {
        int min = -70;
        int max = 70;
        int randomNum;
        Random rn = new Random();
        int range = max - min + 1;
        randomNum =  rn.nextInt(range) + min;
        return Velocity.fromAngleAndSpeed(randomNum, 7);
    }
}
