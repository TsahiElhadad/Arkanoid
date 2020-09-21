/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**.
 * Class which hold the end screen, in cases of:
 * if no more lives - "Game Over. Your score is X"
 * if the blocks finished in the last level- "You Win! Your score is X".
 */
public class EndScreen implements Animation {
    private int score;
    // true if win. false either.
    private boolean isWin;
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**.
     * Constructor for Endscreen.
     * @param score - score of the player.
     * @param isWin - flag which: true if win, false - game over.
     * @param keyboardSensor - keyboard sensor.
     */
    public EndScreen(int score, boolean isWin, KeyboardSensor keyboardSensor) {
        this.score = score;
        this.isWin = isWin;
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
    }

    /**.
     * Function that in charge of the logic frame of the game.
     * @param d - the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        if (this.isWin) {
            d.setColor(Color.MAGENTA);
            d.drawText(60, 200, "You Win! Your score is " + this.score, 40);
            d.drawText(60, 250, "--press space to exit--", 32);
        } else {
            d.setColor(Color.RED);
            d.drawText(60, 200, "Game Over! Your score is " + this.score, 40);
            d.drawText(60, 250, "--press space to exit--", 32);
        }
    }

    /**.
     * Function that return this stop if need to close the screen.
     * @return - this stop, true for end screen, either false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
