/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package geometry;

import java.util.ArrayList;
import java.util.List;

/**.
 * Geometry.Rectangle class.
 * Class which represent Geometry.Rectangle object.
 */
public class Rectangle {
    // height of the rectangle
    private double height;
    // width of the rectangle
    private double width;
    // upper left point of the rectangle.
    private Point upperLeft;
    // upper line of rectangle.
    private Line upperLine;
    // the bottom line of rectangle.
    private Line bottomLine;
    // the right line of rectangle.
    private Line rightLine;
    // the left line of rectangle.
    private Line leftLine;

    /**.
     * Constructor for the Geometry.Rectangle, create new rectangle,
     * @param upperLeft - upper left point of the rectangle.
     * @param width - width of the rectangle.
     * @param height - height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        // make the upper line of rectangle.
        this.upperLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width,
                upperLeft.getY());
        // make the Bottom line of rectangle.
        this.bottomLine = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX()
                + width, upperLeft.getY() + height);
        // make the left line of rectangle.
        this.leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY()
        + height);
        // make the right line of rectangle.
        this.rightLine = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**.
     * Function that calculate list of the intersection points with the line.
     * we get and this rectangle.
     * @param line - Geometry.Line to check intersection with rectangle.
     * @return - list of the intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // create new list to store the intersection points.
        List<Point> list = new ArrayList<Point>();
        // get the intersection point with the upper line of rectangle.
        Point p1 = line.intersectionWith(this.upperLine);
        // get the intersection point with the bottom line of rectangle.
        Point p2 = line.intersectionWith(this.bottomLine);
        // get the intersection point with the left line of rectangle.
        Point p3 = line.intersectionWith(this.leftLine);
        // get the intersection point with the right line of rectangle.
        Point p4 = line.intersectionWith(this.rightLine);
        // it could be 0,1,2 intersection points of line with rectangle.
        // if the line is not intersecting with rectangle.
        if (p1 == null && p2 == null && p3 == null && p4 == null) {
            return list;
        }
        // if we have intersection point.
        if (p1 != null) {
            list.add(p1);
        } // if p2 is not null and if its not in the list.
        if (p2 != null && !ifPointInTheList(p2, list)) {
            list.add(p2);
        } // if p3 is not null and if its not in the list.
        if (p3 != null && !ifPointInTheList(p3, list)) {
            list.add(p3);
        } // if p4 is not null and if its not in the list.
        if (p4 != null && !ifPointInTheList(p4, list)) {
            list.add(p4);
        }
        return list;
    }

    /**
     * Function that check if Geometry.Point is in the list we get.
     * @param p2 - the point to check.
     * @param list - get list of intersection points.
     * @return - true- if point is in the list. false- if not.
     */
    private boolean ifPointInTheList(Point p2, java.util.List<Point> list) {
        // loop over the points in the list, check if equale to p2.
        for (Object p : list) {
            if (((Point) p).equals(p2)) {
                return true;
            }
        }
        return false;
    }

    /**.
     * Function that return the width of the rectangle.
     * @return - this width of rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Function that return the height of the rectangle.
     * @return - this height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**.
     * Function that returns the upper-left point of the rectangle.
     * @return - upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**.
     * return the upper line of rectangle.
     * @return - return the upper line of rectangle.
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * return the bottom line of rectangle.
     * @return - the bottom line of rectangle.
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

    /**.
     * Function that return the left line of rectangle.
     * @return - left line of rectangle.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**.
     * Function that return the right line of rectangle.
     * @return - left line of rectangle.
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * Function that set the upper point of rectangle.
     * @param p - get new upper left point.
     */
    public void setRectangleUpperP(Point p) {
        // setting up upperLeft Point
        this.upperLeft = p;
    }
}
