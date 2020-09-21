/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**.
 * Class Which represent the background of DirecteHit level 1;
 */
public class DirecteHitBackground implements Sprite {
    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 600;
    private static final int WIDTH_RECS_BORDERS = 25;

    /**.
     * Function that draw the sprite to the screen.
     * @param d - the DrawSurface object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // draw the background in black.
        d.setColor(Color.BLACK);
        d.fillRectangle(WIDTH_RECS_BORDERS, WIDTH_RECS_BORDERS + 30,
                WIDTH_SCREEN - (2 * WIDTH_RECS_BORDERS), HEIGHT_SCREEN);
        d.drawText(560, 24, "Level Name: DirectHit", 18);
        // draw the big circle 1.
        d.setColor(Color.BLUE);
        d.drawCircle(WIDTH_SCREEN / 2, (HEIGHT_SCREEN / 4) + WIDTH_RECS_BORDERS, 120);
        // draw smaller circle than circle 1, less radius : circle 2.
        d.drawCircle(WIDTH_SCREEN / 2, (HEIGHT_SCREEN / 4) + WIDTH_RECS_BORDERS, 100);
        // draw smaller circle than circle 2, less radius : circle 3.
        d.drawCircle(WIDTH_SCREEN / 2, (HEIGHT_SCREEN / 4) + WIDTH_RECS_BORDERS, 80);
        // draw Vertical LINE in the middle
        d.drawLine(WIDTH_SCREEN / 2, 30 + WIDTH_RECS_BORDERS, WIDTH_SCREEN / 2, 150);
        d.drawLine(WIDTH_SCREEN / 2, 190, WIDTH_SCREEN / 2, 310);
        // draw line parallel.
        d.drawLine((WIDTH_SCREEN / 2) + 20, (HEIGHT_SCREEN / 4) + WIDTH_RECS_BORDERS,
                (WIDTH_SCREEN / 2) + 140, (HEIGHT_SCREEN / 4) + WIDTH_RECS_BORDERS);
        d.drawLine((WIDTH_SCREEN / 2) - 140, (HEIGHT_SCREEN / 4) + WIDTH_RECS_BORDERS,
                (WIDTH_SCREEN / 2) - 20, (HEIGHT_SCREEN / 4) + WIDTH_RECS_BORDERS);
    }

    /**.
     * does nothing.
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
