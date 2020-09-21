/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**.
 * Represent Lives in the game.
 */
public class LivesIndicator implements Sprite {
    private static final int WIDTH_RECS_BORDERS = 20;
    // rectangle object for the top left rectangle.
    private geometry.Rectangle rectLives;
    // current lives
    private Counter lives;

    /**.
     * Constructor for lives indicator.
     * @param lives - get current lives.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**.
     * Function that draw the sprite to the screen.
     * @param d - the DrawSurface object.
     */
    @Override
    public void drawOn(DrawSurface d) {
       d.setColor(Color.black);
        String strLives = "Lives: " + this.lives.getValue();
        // draw the score
        d.drawText(20, WIDTH_RECS_BORDERS + 4,
                strLives, 20);
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
