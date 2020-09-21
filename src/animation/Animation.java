/**
 * @author Tzahi elhadad
 * ID 206214165
 */

package animation;

import biuoop.DrawSurface;

/**.
 * Interface for the animation project.
 */
public interface Animation {
    /**
     * Function that in charge of the logic frame of the game.
     * @param d - the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**.
     * Function that check if the game should stop or not.
     * @return - true for stopping the game and false continue the game.
     */
    boolean shouldStop();
}
