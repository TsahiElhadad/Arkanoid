/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
import java.awt.Color;

/**.
 * Class will be in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    // rectangle object for the top rectangle.
    private geometry.Rectangle rectScore;
    // current score
    private Counter score;
    // width of the screen
    private static final int WIDTH = 800;

    /**.
     * Constructor for game.ScoreIndicator.
     * @param score - get counter score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        Point topLeft = new Point(0, 0);
        this.rectScore = new geometry.Rectangle(topLeft, WIDTH, 30);
    }

    /**
     * Function that draw the sprite to the screen.
     * @param d - the DrawSurface object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // draw inside the rectangle.
        d.setColor(Color.white);
        d.fillRectangle((int) rectScore.getUpperLeft().getX(), (int) rectScore.getUpperLeft().getY(),
                (int) rectScore.getWidth(),
                (int) rectScore.getHeight());
        d.setColor(Color.black);
        // draw borders of score rectangle.
        d.drawRectangle((int) rectScore.getUpperLeft().getX(), (int) rectScore.getUpperLeft().getY(),
                (int) rectScore.getWidth(),
                (int) rectScore.getHeight());
        // string of the score
        String strScore = "Score: " + this.score.getValue();
        // draw the score
        d.drawText((int) (this.rectScore.getWidth() / 2) - 50,
                (int) 24, strScore, 20);
    }

    /**.
     * do nothing.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Function that adding sprite object to game.
     * @param g - game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
