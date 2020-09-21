/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * decorator-class that will wrap an existing animation
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private boolean isAlreadyPressed;

    /**.
     * Constuctor for KeyPressStoppableAnimation.
     * @param sensor - keyboard sensor.
     * @param key - key.
     * @param animation - animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**.
     * Function that in charge of the logic frame of the game.
     * @param d - the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key)) {
            // fix bug - check if isAlreadyPressed is false.
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }
        if (!this.keyboardSensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**.
     * Function that check if the game should stop or not.
     * @return - true for stopping the game and false continue the game.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
