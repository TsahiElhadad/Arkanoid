/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package interfaces;

import geometry.Point;
import geometry.Rectangle;
import game.Ball;
import game.Velocity;
/**.
 * interfaces.Collidable interface will be used by things that can be collided with.
 * like block, paddle.
 */
public interface Collidable {
    /**
     * Function that return the "collision shape" of the object.
     * @return - "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Function that notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint - the collision point.
     * @param currentVelocity - the cuurent vellocity of the object.
     * @param hitter - get ball hitter.
     * @return - new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
