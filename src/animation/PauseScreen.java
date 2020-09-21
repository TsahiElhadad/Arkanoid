/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * Class for pause the screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**.
     * Constructor for PauseScreen
     * @param k - get keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**.
     * function that draw pause screen when pausing.
     * @param d - get DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /***.
     * Function that determine when to stop the screen.
     * @return - this value of stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
