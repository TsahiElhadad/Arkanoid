/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import Menu.HighScoreAnimation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import io.HighScoreFile;
import levels.LevelInformation;
import java.util.List;

/**
 * Class will be in charge of creating the different levels,and moving from one level to the next.
 */
public class GameFlow {
    private KeyboardSensor keyboard;
    private AnimationRunner animationRunner;
    private Counter counterLives;
    private Counter counterScore;

    /**
     * Constructor for GameFlow.
     * @param ar - animation runner.
     * @param ks - keyboard sensor.
     * @param numOfLives - number of lives.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int numOfLives) {
        this.keyboard = ks;
        this.animationRunner = ar;
        this.counterLives = new Counter(numOfLives);
        this.counterScore = new Counter();
    }

    /**
     * Function that get list of levelInformation and run the
     * levels according to the list we get.
     * @param levels - list of levelInformation.
     */
    public void runLevels(List<LevelInformation> levels) {
        // flag to check if lose or win.
        boolean ifWin = true;
        // play the levels of the game.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard,
                    this.animationRunner, this.counterLives, this.counterScore);
            level.initialize();
            // if we have more blocks and more lives in the game, keep playing.
            while ((level.getReminCounterBlockLevel() > 0) && level.getRemindCounterLives() > 0) {
                level.playOneTurn();
                break;
            } // if no more life the game is OVER.
            if (level.getRemindCounterLives() <= 0) {
                ifWin = false; // flag for lose.
                // load the score.
                HighScoreFile highScoreFile = new HighScoreFile(this.counterScore.getValue());
                HighScoreAnimation highScoreAnimation = new HighScoreAnimation(highScoreFile.getHightScore());
                // show end screen window of LOSE.
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.counterScore.getValue(), false, this.keyboard)));
                // go to highScore screen.
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        highScoreAnimation));
            }
        }
        // load the new score.
        new HighScoreFile(this.counterScore.getValue()).addNewScore();
        HighScoreFile highScoreFile = new HighScoreFile(this.counterScore.getValue());
        HighScoreAnimation highScoreAnimation = new HighScoreAnimation(highScoreFile.getHightScore());
        if (ifWin) { // check if we win.
            // case of win, use KeypressStoppable.
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new EndScreen(this.counterScore.getValue(), true, this.keyboard)));
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    highScoreAnimation));
        }
        ifWin = true;
    }
}
