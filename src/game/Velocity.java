/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import geometry.Point;

/**.
 * game.Velocity class.
 * The class will represent a game.Velocity, with his features: dx values and dy value.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**.
     * Constructor of Class velocity.
     * @param dx - increase x value to point.
     * @param dy - increase y value to point.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Function that get point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * @param p - Geometry.Point (x,y)
     * @return - new Geometry.Point with (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**.
     * Function that return the dx value.
     * @return dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Function that return the dy value.
     * @return dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Function that return the speed by pitagoras sentence.
     * @return - the speed of velocity.
     */
    public double getSpeed() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }

    /**.
     * Function that convert from angle and speed velicity to x,y values for graphics coordinates.
     * @param angle - coordinate of angle to move.
     * @param speed - coordinate of speed to move.
     * @return - velocity in values of x,y.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx;
        double dy;
        // calculate the angle by radian
        double angleByRadian = Math.toRadians(angle);
        // if we get angle 90 go right.
        if (angle == 90) {
            dx = speed;
            dy = 0;
            return new Velocity(dx, dy);
        } // if we get angle 0 go up.
        if (angle == 0) {
            dx = 0;
            dy = -speed;
            return new Velocity(dx, dy);
        } // if we get angle 180 turn down.
        if (angle == 180) {
            dx = 0;
            dy = speed;
            return new Velocity(dx, dy);
        } // if we get 270 turn left.
        if (angle == 270) {
            dx = -speed;
            dy = 0;
            return new Velocity(dx, dy);
        }
        // calculate the x,y values with trigonomatric solutions according to the deamens.
        dx = speed * Math.sin(Math.toRadians(angle));
        dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
