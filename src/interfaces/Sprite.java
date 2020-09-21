/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package interfaces;

import biuoop.DrawSurface;
import game.GameLevel;

/**.
 * interfaces.Sprite interface,all sprites can be drawn on the screen, and can be notified
 * that time has passed (so that they know to change their position / shape
 * / appearance / etc). In our design, all the game objects (game.Ball, game.Block, game.Paddle)
 * are Sprites -- they will implement the interfaces.Sprite interface.
 */
public interface Sprite {
    /**
     * Function that draw the sprite to the screen.
     * @param d - the DrawSurface object.
     */
    void drawOn(DrawSurface d);

    /**.
     * Function that notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Function that adding sprite object to game.
     * @param g - game.
     */
    void addToGame(GameLevel g);
}
