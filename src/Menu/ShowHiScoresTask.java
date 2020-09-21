package Menu;

import animation.Animation;
import animation.AnimationRunner;
import interfaces.Task;

/**
 * Class represent the chose of table scores.
 */
public class ShowHiScoresTask implements Task<Void> {
    private final AnimationRunner runner;
    private final Animation highScoresAnimation;

    /**
     * Constructor for ShowHiScoresTask class.
     * @param runner - get animation runner.
     * @param highScoresAnimation - get highScoresAnimation.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**.
     * run the high score animation.
     * @return - null.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
