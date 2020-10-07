/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.Sprite;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import java.awt.Color;
import java.util.List;
import java.util.Random;

/**.
 * game.Game class.
 *  The class will hold the sprites and the collidables,
 *  and will be in charge of the animation of the game.
 */
public class GameLevel implements Animation {
    private LevelInformation levelInfo;
    // runner the animation.
    private AnimationRunner runner;
    // flag for check running.
    private boolean running;
    // the sprite collection of the game.
    private SpriteCollection sprites;
    // the game enviornment of the game.
    private GameEnvironment environment;
    // keyboard sensor
    private biuoop.KeyboardSensor keyboard;
    // number of remained blocks
    private Counter countRemindBlocks;
    // number of remained balls
    private Counter countRemindBalls;
    // score of the player.
    private Counter countScore;
    // lives of the player
    private Counter livesCounter;
    // make paddle
    private Paddle myPaddle;
    // width and height for the Gui
    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 600;
    // width of the rectangles borders.
    private static final int WIDTH_RECS_BORDERS = 25;
    // radius for the balls
    private static final int BALL_RADIUS = 5;
    // speed for the ball.
    private static final int BALL_SPEED = 9;
    // height for the game.Paddle
    private static final int PADDLE_HEIGHT = 17;

    /**
     * Constructor for the game level. initial the following proporties:
     * @param levelInformation - level information.
     * @param keyboard - keyBoard sensor.
     * @param animationRunner - animation runner.
     * @param counterLives - counter lives in the game.
     * @param counterScore - score in the game.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard,
                    AnimationRunner animationRunner, Counter counterLives, Counter counterScore) {
        this.levelInfo = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.countRemindBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        this.countRemindBalls = new Counter();
        this.countScore = counterScore;
        this.livesCounter = counterLives;
        this.runner = animationRunner;
        // get the keyboard from runner that have the GUI.
        this.keyboard = keyboard;
        this.running = true;
    }

    /**.
     * Function that add collidable to the game.
     * @param c - a collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * * Function that add sprite to the game.
     * @param s - sprite object.
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.sprites.addSprite(s);
        } else {
            System.out.println("addSprite get null");
        }
    }

    /**.
     * Function that initialize a new game: create the Blocks and game.Ball (and game.Paddle).
     */
    public void initialize() {
        // create game.Block remover
        BlockRemover blockRemover = new BlockRemover(this, this.countRemindBlocks);
        // create ball remover
        BallRemover ballRemover = new BallRemover(this, this.countRemindBalls);
        // create score tracking for the player.
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.countScore);
        // make: LEFT BORDER, RIGHT BORDER, TOP BORDER
        makeBordersBlocks();
        // add ScoreIndicator to game
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.countScore);
        scoreIndicator.addToGame(this);
        this.levelInfo.getBackground().addToGame(this);
        // add LiveIndicator to game.
        LivesIndicator livesIndicator = new LivesIndicator(this.livesCounter);
        livesIndicator.addToGame(this);

        // create the BOTTOM border block, for DEATH ZONE of ball.
        Point pointBottomBorder = new Point(WIDTH_RECS_BORDERS, HEIGHT_SCREEN);
        Rectangle recBottomBorder = new Rectangle(pointBottomBorder,
                WIDTH_SCREEN - (2 * WIDTH_RECS_BORDERS), WIDTH_RECS_BORDERS);
        Block blockBottomBorder = new Block(recBottomBorder, Color.GRAY);
        // add the block to the game.
        blockBottomBorder.addToGame(this);
        // make death zone of bottom block as a listener of ball remover.
        blockBottomBorder.addHitListener(ballRemover);

        List<Block> listBlocks = levelInfo.blocks();
        for (Block b : listBlocks) {
            b.addToGame(this);
            // add the block to Hitlitener
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Function that making the top, left, right borders blocks of the screen.
     */
    private void makeBordersBlocks() {
        // create the borders of the screen.
        // create the top border block.
        Point pointTopBorder = new Point(0, 0);
        Rectangle recTopBorder = new Rectangle(pointTopBorder, WIDTH_SCREEN,
                WIDTH_RECS_BORDERS + 30);
        Block blockTopBorder = new Block(recTopBorder, Color.GRAY);

        // create the left border block.
        Point pointLeftBorder = new Point(0, WIDTH_RECS_BORDERS);
        Rectangle recLeftBorder = new Rectangle(pointLeftBorder, WIDTH_RECS_BORDERS,
                HEIGHT_SCREEN - WIDTH_RECS_BORDERS);
        Block blockLeftBorder = new Block(recLeftBorder, Color.GRAY);

        // create the right border block.
        Point pointRightBorder = new Point(WIDTH_SCREEN - WIDTH_RECS_BORDERS, WIDTH_RECS_BORDERS);
        Rectangle recRightBorder = new Rectangle(pointRightBorder, WIDTH_RECS_BORDERS,
                HEIGHT_SCREEN - WIDTH_RECS_BORDERS);
        Block blockRightBorder = new Block(recRightBorder, Color.GRAY);

        // add blocks to the game.
        blockTopBorder.addToGame(this);
        blockLeftBorder.addToGame(this);
        blockRightBorder.addToGame(this);
    }

    /**
     * Function that making paddle for the game.
     * @return - new paddle.
     */
    private Paddle makePaddle() {
        // point for the paddle.
        Point pPaddle = new Point((WIDTH_SCREEN - levelInfo.paddleWidth()) / 2,
                HEIGHT_SCREEN - (WIDTH_RECS_BORDERS));
        // rectangle for the paddle.
        Rectangle recPaddle = new Rectangle(pPaddle, levelInfo.paddleWidth(), PADDLE_HEIGHT);
        return new Paddle(recPaddle, new Color(255, 204, 0), this.keyboard,
                this.levelInfo.paddleSpeed(), this.levelInfo.paddleWidth());
    }

    /**
     * Function that making number of balls according to number we get.
     * @param numOfBalls - number of balls.
     */
    private void makeBall(int numOfBalls) {
        for (int i = 0; i < numOfBalls; i++) {
            Point pBall = new Point(WIDTH_SCREEN / 2, 500);
            Ball ball1 = new Ball(pBall, BALL_RADIUS, Color.WHITE);
            // get the velocity of ball in index i.
            ball1.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball1.addToGame(this);
            ball1.setGameEnvironment(this.environment);
            this.countRemindBalls.increase(1);
        }
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
        return Velocity.fromAngleAndSpeed(randomNum, BALL_SPEED);
    }

    /**.
     * Function that generate random color.
     * @return - random color.
     */
    private java.awt.Color getRandomColor() {
        return new Color((int) (Math.random() * 0x1000000));
    }

    /**.
     * Function that run one turn of the game.
     */
    public void playOneTurn() {
        // reset balls and paddle
        this.createBallsOnTopOfPaddle();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // run the animation runner.
        this.runner.run(this);
        // if the balls is over, also we have more life. play again.
        if (this.countRemindBalls.getValue() == 0
            && this.livesCounter.getValue() != 0) {
            // if paddle already created, remove it from game, and playOneTurn will make new one.
            if (this.myPaddle != null) {
                this.myPaddle.removeFromGame(this);
            }
            playOneTurn();
        } // if lives is over, end screen game over.
//        if (this.livesCounter.getValue() == 0) {
////            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
////                    new HighScoreFile(this.countScore.getValue())));
//
//            // add the new score.
//            new HighScoreFile(this.countScore.getValue()).addNewScore();
//            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
//                    new EndScreen(this.countScore.getValue(), false, this.keyboard)));
//            // for return to menu.
//            HighScoreFile highScoreFile = new HighScoreFile(this.countScore.getValue());
//            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
//                    new HighScoreAnimation(highScoreFile.getHightScore())));
//            //this.runner.getGui().close();
    }

    /**.
     * Function that restart the game, make balls and the paddle in the center.
     */
    private void createBallsOnTopOfPaddle() {
        // make number of ball level.
        makeBall(levelInfo.numberOfBalls());
        // make paddle.
        this.myPaddle = makePaddle();
        this.myPaddle.addToGame(this);
    }

        /**.
         * Function that removing the interfaces.Collidable object c that we get
         * from the game environment.
         * @param c - collidable object.
         */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

        /**.
         * Function that removing the interfaces.Sprite object s that we get
         * from the interfaces.Sprite collection.
         * @param s - ..
         */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**.
     * Function that in charge of the logic frame of the game.
     * @param d - the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // draw all sprites.
        this.sprites.drawAllOn(d);
        // time passed - let sprites move one step
        this.sprites.notifyAllTimePassed();
        // feature - pause the game
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
        // if we did'nt have more blocks game over.
        if (this.countRemindBlocks.getValue() == 0) {
            // get 100 points
            this.countScore.increase(100);
            this.running = false;
        }
        // if we d'ont have any more balls.
        if (this.countRemindBalls.getValue() == 0) {
            // lives is minus 1.
            this.livesCounter.decrease(1);
            // stop run.
            this.running = false;
        }
    }

    /**.
     * Function that check if the game should stop or not.
     * @return - true for stopping the game and false continue the game.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**.
     * Function get remind blocks in the game level.
     * @return - remind blocks.
     */
    public int getReminCounterBlockLevel() {
        return this.countRemindBlocks.getValue();
    }

    /**.
     * Function that return the number of lives of counter.
     * @return - remind lives in the game.
     */
    public int getRemindCounterLives() {
        return this.livesCounter.getValue();
    }
}
