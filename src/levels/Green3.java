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
 * Class that represent level 2.
 */
public class Green3 implements LevelInformation {
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
    private static final int WIDTH_RECS_BORDERS = 25;
    // block width and height for the blocks inside the screen.
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;

    /**.
     * Constructor for Green3 class, initiate the properties of level 2.
     */
    public Green3() {
        this.numberOfBalls = 2;
        this.listBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = 5;
        this.paddleWidth = 80;
        this.levelName = "Green 3";
        this.background = new Green3Background();
        this.listOfBlocks = new ArrayList<Block>();
        this.numberOfBlocksToRemove = 40;
    }

    /**.
     * the number of balls in the level is 2;
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
        // craete the blocks inside the screen according to level.
        int numOfBlockInROW = 10;
        // num of rows of blocks.
        int levelSize = 5;
        // x,y values for upperleft point for creating the blocks.
        int x = WIDTH_SCREEN - WIDTH_RECS_BORDERS - BLOCK_WIDTH;
        int y = 160;
        // level of 6 rows.
        for (int i = 0; i < levelSize; i++) {
            // switch for each line the accurate color.
            Color color = Color.ORANGE;
            switch (i) {
                case (0):
                    color = Color.GRAY;
                    break;
                case (1):
                    color = Color.RED;
                    break;
                case (2):
                    color = Color.YELLOW;
                    break;
                case (3):
                    color = Color.BLUE;
                    break;
                case (4):
                    color = Color.WHITE;
                    break;
                default:
            }
            // create numOfBlockInRow blocks in each row.
            for (int j = 0; j < numOfBlockInROW; j++) {
                Point upperLeft = new Point(x, y);
                Rectangle rec = new Rectangle(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rec, color);
                this.listOfBlocks.add(block);
                // decrease the x value for the next point.
                x = x - BLOCK_WIDTH;
            } // go to down row.
            numOfBlockInROW--;
            // initial the x values for start new raw.
            x = WIDTH_SCREEN - WIDTH_RECS_BORDERS - BLOCK_WIDTH;
            y = y + BLOCK_HEIGHT;
        }
        return this.listOfBlocks;
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
