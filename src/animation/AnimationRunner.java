/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class for AnimationRunner - run the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**.
     * Constructor for the AnimationRunner. making Gui and sleeper.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid Game", 800, 600);
        // using 60 frames per second.
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Loop animation that run the animation.
     * @param animation - get animation object to run.
     */
    public void run(Animation animation) {
        // we want a smooth animations that displays 60 different frames in a second.
        // each frame in the animation can last 1000 / framesPerSecond milliseconds.
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**.
     * Function that get keyboard sensor of this gui.
     * @return - this keyboard sensor.
     */
    public biuoop.KeyboardSensor getKeyboard() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * Function that return this gui.
     * @return - this gui.
     */
    public GUI getGui() {
        return this.gui;
    }
}
