/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import interfaces.Sprite;
import java.awt.Color;

/**.
 * Class which represent wild easy level background.
 */
public class WildEasyBackground implements Sprite {
    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 600;
    private static final int WIDTH_RECS_BORDERS = 25;

    /**.
     * Function that draw the sprite to the screen.
     * @param d - the DrawSurface object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(560, 24, "Level Name: Wide Easy", 18);
        // draw background screen.
        d.setColor(Color.WHITE);
        d.fillRectangle(WIDTH_RECS_BORDERS, WIDTH_RECS_BORDERS + 30,
                WIDTH_SCREEN - (2 * WIDTH_RECS_BORDERS), HEIGHT_SCREEN);
        // draw lines from the sun
        Point pStart = new Point(130, 130);
        Point pOver = new Point(WIDTH_RECS_BORDERS, (HEIGHT_SCREEN / 3) + 50);
        d.setColor(new Color(255, 244, 0));
        for (int i = 0; i < 100; i++) {
            d.drawLine((int) pStart.getX(), (int) pStart.getY(),
                    (int) pOver.getX(), (int) pOver.getY());
            pOver = new Point(pOver.getX() + 6, (HEIGHT_SCREEN / 3) + 50);
        }
        // draw the sun
        d.setColor(new Color(255, 255, 150));
        d.fillCircle(130, 130, 50);
        d.setColor(new Color(255, 215, 100));
        d.fillCircle(130, 130, 45);
        d.setColor(new Color(255, 204, 0));
        d.fillCircle(130, 130, 40);
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
