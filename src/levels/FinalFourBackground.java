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

/**
 * Class which represent Final Four level 4 background.
 */
public class FinalFourBackground implements Sprite {
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
        d.drawText(560, 24, "Level Name: Final Four", 18);
        // draw background screen.
        d.setColor(new Color(51, 204, 255));
        d.fillRectangle(WIDTH_RECS_BORDERS, WIDTH_RECS_BORDERS + 30,
                WIDTH_SCREEN - (2 * WIDTH_RECS_BORDERS), HEIGHT_SCREEN);
        // draw lines from the clouds.
        geometry.Point pStart = new geometry.Point(90, 450);
        geometry.Point pOver = new geometry.Point(70, HEIGHT_SCREEN);
        d.setColor(Color.white);
        for (int i = 0; i < 11; i++) {
            d.drawLine((int) pStart.getX(), (int) pStart.getY(),
                    (int) pOver.getX(), (int) pOver.getY());
            pStart = new Point(pStart.getX() + 8, pStart.getY());
            pOver = new Point(pOver.getX() + 8, pOver.getY());
        }
        // draw clouds
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(90, 450, 20);
        d.fillCircle(105, 470, 20);
        d.setColor(new Color(153, 153, 153));
        d.fillCircle(120, 450, 25);
        d.setColor(new Color(102, 102, 102));
        d.fillCircle(135, 470, 20);
        d.fillCircle(150, 450, 25);
        // Right clouds, the same likes above.
        pStart = new geometry.Point(600, 540);
        pOver = new geometry.Point(580, HEIGHT_SCREEN);
        d.setColor(Color.white);
        for (int i = 0; i < 11; i++) {
            d.drawLine((int) pStart.getX(), (int) pStart.getY(),
                    (int) pOver.getX(), (int) pOver.getY());
            pStart = new Point(pStart.getX() + 7, pStart.getY());
            pOver = new Point(pOver.getX() + 7, pOver.getY());
        }
        // draw clouds Right
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(600, 520, 20);
        d.fillCircle(615, 540, 20);
        d.setColor(new Color(153, 153, 153));
        d.fillCircle(630, 520, 25);
        d.setColor(new Color(102, 102, 102));
        d.fillCircle(645, 540, 20);
        d.fillCircle(660, 520, 25);
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
