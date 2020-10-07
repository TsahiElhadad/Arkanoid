package menu;
import animation.Animation;
import biuoop.DrawSurface;
import java.awt.*;

public class HighScoreAnimation implements Animation {
    private int highScore;
    private boolean stop;

    /**.
     * Constructor for HighScoreAnimation.
     * @param highScore - highscore animation.
     */
    public HighScoreAnimation(int highScore) {
        this.highScore = highScore;
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
        d.setColor(Color.BLACK);
        d.drawText(200, 250, "The high Score of the game is: " + this.highScore, 25);
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
