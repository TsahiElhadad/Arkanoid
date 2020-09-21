/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package interfaces;

/**.
 * Interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - listener
     */
    void addHitListener(HitListener hl);

    /**
     * function that Removing hl from the list of listeners to hit events.
     * @param hl - HitListener object.
     */
    void removeHitListener(HitListener hl);
}
