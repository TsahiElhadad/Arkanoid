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
 * Class that represent the background of Green 3 level 2;
 */
public class Green3Background implements Sprite {
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
        d.drawText(560, 24, "Level Name: Green 3", 18);
        d.setColor(new Color(0x256C16));
        d.fillRectangle(WIDTH_RECS_BORDERS, WIDTH_RECS_BORDERS + 30,
                WIDTH_SCREEN - (2 * WIDTH_RECS_BORDERS), HEIGHT_SCREEN);
        d.setColor(new Color(0x21C16));
        d.fillRectangle(85, 375, 45, 80);
        d.setColor(new Color(0x22C16));
        d.fillRectangle(100, 245, 15, 130);
        // draw the circles.
        d.setColor(Color.ORANGE);
        d.fillCircle(108, 240, 10);
        d.setColor(Color.RED);
        d.fillCircle(108, 240, 7);
        d.setColor(Color.WHITE);
        d.fillCircle(108, 240, 3);
        // draw the background of the rec
        d.setColor(Color.BLACK);
        d.fillRectangle(47, 447, 114, 200);
        int numOfRecInROW = 5;
        // num of rows
        int levelSize = 5;
        // x,y values for upperleft point each rectangle.
        Point pHelper = new Point(60, 457);
        for (int j = 0; j < numOfRecInROW; j++) {
            for (int i = 0; i < levelSize; i++) {
                d.setColor(Color.LIGHT_GRAY);
                d.fillRectangle((int) pHelper.getX(), (int) pHelper.getY(), 10, 26);
                pHelper = new Point((int) pHelper.getX() + 20, (int) pHelper.getY());
            }
            pHelper = new Point(60, (int) pHelper.getY() + 30);
        }
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
