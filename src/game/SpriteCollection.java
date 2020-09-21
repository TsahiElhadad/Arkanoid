/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.util.ArrayList;
import java.util.Collection;

/**.
 * game.SpriteCollection class.
 * The class will store all the sprite in the game.
 */
public class SpriteCollection {
    // list of sprite ocjects.
    private Collection<Sprite> spritsList = new ArrayList<Sprite>();

    /**
     * Function that add new spirit to the spirits list.
     * @param s - sprite object.
     */
    public void addSprite(Sprite s) {
        this.spritsList.add(s);
    }

    /**
     * Function that removing spirit from the spirits list.
     * @param s - sprite object.
     */
    public void removeSprite(Sprite s) {
        this.spritsList.remove(s);
    }

    /**.
     * Function that call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // make temp collection.
        Collection<Sprite> tempList = new ArrayList<Sprite>(this.spritsList);
        for (Sprite s : tempList) {
            s.timePassed();
        }
    }

    /**.
     * Function that call drawOn(d) on all sprites.
     * @param d - Drawsurface object.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.spritsList) {
            s.drawOn(d);
        }
    }
}
