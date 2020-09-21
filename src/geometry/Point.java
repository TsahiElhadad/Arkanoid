/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package geometry;

/**.
 * Geometry.Point class.
 * The class will represent a Geometry.Point, with his features: x and y values.
 */
public class Point {
    private double x;
    private double y;

    /**.
     * Constructor for point, initial the x,y values we get.
     * @param x - the x value.
     * @param y - the y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**.
     * Function that calculate the distance of this point to the other point by
     * math solution.
     * @param other - the other point for the distance calculate.
     * @return - the distance between the 2 points.
     */
    public double distance(Point other) {
        return Math.sqrt(((other.x - this.x) * (other.x - this.x))
                + ((other.y - this.y) * (other.y - this.y)));
    }

    /**
     * Function that check if 2 points are eqauls, by compares values of x,y of the points.
     * @param other - the other point to be compared.
     * @return - true - if equals. false - if not.
     */
    public boolean equals(Point other) {
        if ((other.x == this.x) && (other.y == this.y)) {
            return true;
        }
        return false;
    }

    /**.
     * Function for get the x value of point.
     * @return - the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**.
     * Function for get the y value of point.
     * @return - the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
