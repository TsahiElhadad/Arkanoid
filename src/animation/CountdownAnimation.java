/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.SpriteCollection;
import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    // the amount of time the animation should run
    private double numOfSeconds;
    // from the second we count down.
    private int countFrom;
    // boolean flag to know when to stop.
    private boolean stop;
    // will help me save the seconds per object of 1,2,3,go.
    private long helperCountFrom;
    // for drawing the game
    private SpriteCollection gameScreen;
    // for waiting.
    private Sleeper sleeper;

    /**.
     * Constructor for CountdownAnimation.
     * @param numOfSeconds - seconds to wait.
     * @param countFrom - number to count from.
     * @param gameScreen - the sprite collection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
        this.helperCountFrom = (long) (this.numOfSeconds / countFrom);
    }

    /**
     * Function that in charge of the draw 3,2,1 go.
     * @param d - the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // first draw the sprites.
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(196, 2, 178));
        // for not white screen in the start.
        if (countFrom == 3) {
            d.drawText(d.getWidth() / 2 - 25, d.getHeight() / 2,
                    Integer.toString(this.countFrom), 100);
        }
        // if count from is bigger than 0, saw draw text of countFrom.
        if (countFrom != 3 && countFrom > 0) {
            d.drawText(d.getWidth() / 2 - 25, d.getHeight() / 2,
                    Integer.toString(this.countFrom), 100);
            this.sleeper.sleepFor(this.helperCountFrom);
        }
        // if cont from is 0, print go.
        if (countFrom == 0) {
            d.drawText(d.getWidth() / 2 - 70, d.getHeight() / 2, "GO", 100);
            this.sleeper.sleepFor(this.helperCountFrom);
        }
        // if its less than zero wait and stop the running.
        if (countFrom < 0) {
            this.sleeper.sleepFor(this.helperCountFrom);
            this.stop = true;
        }
        // update the count from.
        countFrom--;
    }

    /**
     * Function that check if the game should stop or not.
     * @return - true for stopping the game and false continue the game.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
