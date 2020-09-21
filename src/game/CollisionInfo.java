/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import geometry.Point;
import interfaces.Collidable;

/**.
 * Class game.CollisionInfo.
 */
public class CollisionInfo {
    // the collision point.
    private Point collisionPoint;
    // the object that colliade with.
    private Collidable collisionObject;

    /**.
     * constructor for the class.
     * @param collisionPoint - the collision point.
     * @param collisionObject - the object that colliade with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Function that return the point at which the collision occurs.
     * @return - the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Function that return the collidable object involved in the collision.
     * @return - the collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
