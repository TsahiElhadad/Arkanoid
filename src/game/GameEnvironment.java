/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import java.util.ArrayList;
import java.util.List;

/**.
 * game.GameEnvironment class.
 * The game.GameEnvironment class will be a collection of things that can collide with
 * and get information about the collision.
 */
public class GameEnvironment {
    // list of interfaces.Collidable.
    private List<Collidable> listCollidables;

    /**
     * Constructor for the class, initial array list of collidable.
     */
    public GameEnvironment() {
        this.listCollidables = new ArrayList<Collidable>();
    }

    /**.
     * Function that add colliadble to the enviroment.
     * @param c - collidable object.
     */
    public void addCollidable(Collidable c) {
        listCollidables.add(c);
    }

    /**.
     * Function that remove colliadble from the enviroment.
     * @param c - collidable object.
     */
    public void removeCollidable(Collidable c) {
        listCollidables.remove(c);
    }

    /**
     * Function that get Geometry.Line trajectory and assume an object moving from line.start() to line.end(),
     * check if this object will collide with any of the collidables in the list,
     * if not collide return null, and else, return the information about the closest collision
     * that is going to occur.
     * @param trajectory - the Path of object, line.start() to line.end().
     * @return - ColisionInfo - informatiom about the closest collision that going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // temp point will have the closest intersection point to start of current c object.
        Point p;
        // the collidable object that have the closest intersection point.
        Collidable closestCollidable = null;
        // the closest point to starting point of trajectory.
        Point pClosest = null;
        // loop over all the collidables, and check if there is coliision with trajectory.
        for (Collidable c : this.listCollidables) {
            // get the intersection points list of c(rectangle) with line.
            List<Point> list = (c.getCollisionRectangle()).intersectionPoints(trajectory);
            // if is not empty, there is intersection, find the closest point.
            if (!list.isEmpty()) {
                // get the closest intersection point of trajectory with c collidable.
                p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                // if its the first loop pClosest == null, so initial it.
                if (pClosest == null) {
                    pClosest = p;
                    closestCollidable = c;
                    // get the closest point to trajectory start point.
                } else if (pClosest.distance(trajectory.start()) > p.distance((trajectory.start()))) {
                    pClosest = p;
                    closestCollidable = c;
                }
            }
        }
        // check if we don't have intersection point so return null.
        if (pClosest == null && closestCollidable == null) {
            return null;
        }
        // create collision info and return it.
        CollisionInfo colInfo = new CollisionInfo(pClosest, closestCollidable);
        return colInfo;
    }
}
