/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package geometry;

import java.util.List;

/**.
 * Geometry.Line class.
 * The class will represent a Geometry.Line, with his features: starting point and ending point.
 */
public class Line {
    // starting point of line.
    private Point start;
    // ending point of line.
    private Point end;

    /**
     * constructor for Geometry.Line class.
     * @param start get 1 point that represent start point of line.
     * @param end get 1 point that represent end point of line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * another constructor for Geometry.Line class.
     * @param x1 point 1 rate x.
     * @param y1 point 1 rate y.
     * @param x2 point 2 rate x.
     * @param y2 point 2 rate y.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**.
     *  function for calculate the length of the line
     * @return the length of a line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**.
     * function for calculate the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * function that return the start point of line.
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * function that return the end point of the line.
     * @return end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     *  Function that check if all the values of points in this line and other line are equal.
     * @param other - the other line we need to check.
     * @return true- if the lines are equal. false- if the lines are not equal.
     */
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if ((other.start.getX() == this.start.getX())
                && (other.start.getY() == this.start.getY())
                && (other.end.getX() == this.end.getX())
                && (other.end.getY() == this.end.getY())) {
            return true;
        }
        return false;
    }

    /**.
     * Function that check if rectangle intersect with line, if not intersect return null,
     * else return the closest point to starting point of line.
     * @param rect - rectangle to check.
     * @return - null if we dont have intersection point, else the closest point to start line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // create line from start point and end.
        Line line = new Line(this.start, this.end);
        // the minimum distance of intesection point to start point of line.
        double minDistance;
        // the closest point to starting point.
        Point closePoint;
        // get list of intersection points with line.
        List listOfInterPoint = rect.intersectionPoints(line);
        // check if we don't have intersection point of line and rect return null.
        if (listOfInterPoint.isEmpty()) {
            return null;
        }
        // else, we need to find the closest intersection point to the start line.
        minDistance = ((Point) listOfInterPoint.get(0)).distance(this.start);
        // the first point in the array.
        closePoint = (Point) listOfInterPoint.get(0);
        // loop over the list to get the minimum distance.
        for (Object p : listOfInterPoint) {
            if (minDistance > ((Point) p).distance(this.start)) {
                minDistance = ((Point) p).distance(this.start);
                closePoint = (Point) p;
            }
        }
        return closePoint;
    }

    /**.
     * Function that calculate the relation of the slopes of line that middP point creates with the
     * 2 points separately- staP,endP.
     * the solution in analytic geometry calculate the relation between the slope of
     * a starting point with a specific point in a segment, and the end point with
     * the specific point in the section, and check its directions.
     * the method is using the types of angles created by cases of cooliner, clock, counter clock
     * directions of the slopes.
     * @param staP starting point in the section
     * @param middP specific point in the section
     * @param endP ending point in the section
     * @return the value of relation of the slopes.
     */
    public int direction(Point staP, Point middP, Point endP) {
        // the solution in analytic geometry for check if coolinear/clock/clockwise case.
        double m = (middP.getY() - staP.getY()) * (endP.getX() - middP.getX())
                - (middP.getX() - staP.getX()) * (endP.getY() - middP.getY());
        // the case of coolinear,same slopes and direction.
        if (m == 0) {
            return 0;
        }
        // the case of counter clock, the slope is in positive direction-increasing
        if (m > 0) {
            return 1;
        }
        // the case of clock, the slope is in negative direction-decreasing .
        return 2;
    }

    /**.
     * function that check if 2 Lines are intersecting or not, using a method that checks
     * points in space and their gradient directions, and examines the possible situations
     * where the gradients can really intersect.
     * There are 3 situations that can be created between 3 points -
     * coolinear(continue the same line), counter clock- is direction against the clock
     * and clock- direction with the clock.
     * @param other the line we get for checking if intersecting with this line.
     * @return true- if this line intersecting with other line. false- if line is not intersecting.
     */
    public boolean isIntersecting(Line other) {
        Point p1 = this.start;
        Point q1 = this.end;
        Point p2 = other.start;
        Point q2 = other.end;
        // 4 options can be occur with the directions of 3 Points, Combinatorika.
        // check the cases of cooliner, counter clock, and clock.
        int dirThis1 = direction(p1, q1, p2);
        int dirThis2 = direction(p1, q1, q2);
        int dirOther3 = direction(p2, q2, p1);
        int dirOther4 = direction(p2, q2, q1);
        // using epsilon for accuracy in double.
        double epsilon = 0.001;
        // if the same lines we don't have intersection.
        if (this.equals(other)) {
            return false;
        }
        // if each direction is diffrent the lines will intersect.
        if ((dirThis1 != dirThis2) && (dirOther3 != dirOther4)) {
            return true;
        }
        // the case of all the x are eqauls, check if there is point intersect or not
        if ((Math.abs(p1.getX() - q1.getX()) < epsilon) && (Math.abs(p2.getX() - q2.getX()) < epsilon)
                && (Math.abs(p1.getX() - p2.getX()) < epsilon)) {
            // check the cases of line on line, if its not return true.
            if (!ifLineOnLine(p1.getY(), q1.getY(), p2.getY(), q2.getY())) {
                return true;
            }
        } // the case of all the y are eqauls, check if there is point intersect or not.
        if (Math.abs(p1.getY() - q1.getY()) < epsilon && Math.abs(p2.getY() - q2.getY()) < epsilon
                && Math.abs(p1.getY() - p2.getY()) < epsilon) {
            // check the cases of line on line, if its not return true.
            if (!ifLineOnLine(p1.getX(), q1.getX(), p2.getX(), q2.getX())) {
                return true;
            }
        }
        // the case of 2 line with the same slope, and have one share point
        if ((p1.equals(p2)) || (p1.equals(q2)) || (p2.equals(q1)) || (p2.equals(p1))) {
            // if line in line or not.
            if (!ifLineOnLine(p1.getX(), q1.getX(), p2.getX(), q2.getX())) {
                return true;
            }
        }
        // the lines are not intersect for sure.
        return false;
    }

    /**.
     * Help function that check the cases of 2 lines that the same x or y values,
     * which means they are on the same line, and find if there is an intersection point or
     * is the following cases:
     * 1. Starting from the same point but line is on another line.
     * 2. One line is inside the other line.
     * 3. Some part of one line is on the other line, and they don't have shared points-no intersection.
     * @param s1 - the starting point of this line.
     * @param e1 - the ending point of this line.
     * @param s2 - the starting point of other line.
     * @param e2 - the ending point of other line.
     * @return true- if the line is on the other line-no intersect.
     **********false- if 1 line is end when the other start- so we have intersection.
     */
    private boolean ifLineOnLine(double s1, double e1, double s2, double e2) {
        double epsilon = 0.0001;
        // the case we have the same point and Geometry.Line is on line and continue him- bigger or smaller.
        if ((Math.max(s1, e1) == Math.max(s2, e2)) || (Math.min(s1, e1) == Math.min(s2, e2))) {
            return true;
        }
        // the case line is inside the other line.
        if (((Math.min(s1, e1) < Math.min(s2, e2)) && Math.max(s1, e1) > Math.max(s2, e2))
                || ((Math.min(s1, e1) > Math.min(s2, e2)) && Math.max(s1, e1) < Math.max(s2, e2))) {
            return true;
        }
        // if all the points are diffrent so its have to be line on a line.
        if ((s1 != s2) && (s1 != e2) && (e1 != s2) && (e1 != e2)) {
           return true;
        }
        // the only case that one line finish when the second start, we have intersection.
        return false;
    }

    /**.
     * Help function for the case we have 2 lines with the same slope and intersection point(.----.----.)
     * finding that point that is shared with one of the points.
     * @param thisStart - the starting point of line1.
     * @param thisEnd - the ending point of line2.
     * @param otherStart - the starting point of line2.
     * @param otherEnd - the ending point of line2.
     * @return the shared point of the line.
     * */
    private double findTheXorY(double thisStart, double thisEnd, double otherStart, double otherEnd) {
        // find the shared point in the line.
        if (thisStart == otherStart) {
            return thisStart;
        } else if (thisStart == otherEnd) {
            return thisStart;
        } else {
            return thisEnd;
        }
    }

    /**
     * Function that find the intersection points of this line with other line we get.
     * @param other - the line that we need to find his intersect with this line.
     * @return Geometry.Point that represent the intersection point of two lines.
     */
    public Point intersectionWith(Line other) {
            // check if other == null, or we don't have intersection.
            if (other == null || !this.isIntersecting(other)) {
                return null;
            }
            // epsilon for accuracy in double.
            double epsilon = 0.0001;
            Point p1 = this.start;
            Point q1 = this.end;
            Point p2 = other.start;
            Point q2 = other.end;
            // the x,y points of intersection.
            double x, y;
            // the values of the equation y = mx +b in each line.
            double b1, b2;
            double m1, m2;

            // if the 2 lines are x=c,x=c, find their intersect point
            if ((Math.abs(p1.getX() - q1.getX()) < epsilon) && (Math.abs(p2.getX() - q2.getX()) < epsilon)
                    && (Math.abs(p1.getX() - p2.getX()) < epsilon)) {
                // the same x, the same line x=c
                x = p1.getX();
                // find the y shared point with the according function.
                y = findTheXorY(this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY());
                return new Point(x, y);
            }
            // if the 2 lines are y=c,y=c, find their intersect point
            if (Math.abs(p1.getY() - q1.getY()) < epsilon && Math.abs(p2.getY() - q2.getY()) < epsilon
                    && Math.abs(p1.getY() - p2.getY()) < epsilon) {
                // the same y, the same line y=c
                y = p1.getY();
                // find the x shared point with the according function.
                x = findTheXorY(this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX());
                return new Point(x, y);
            }
            // if "this" Geometry.Line is in the form of x=c, for not to divide by zero
            if (Math.abs(p1.getX() - q1.getX()) < epsilon) {
                // the line is in the form x=c, so the same x.
                x = p1.getX();
                // calculate the slope of other.
                m1 = getSlope(other);
                // Solution to find the 'b' value of equation y = mx+b
                b1 = p2.getY() - (m1 * p2.getX());
                // find the y by the equation.
                y = (m1 * x) + b1;
                return new Point(x, y);
            }
            // if "other" Geometry.Line is in the form of x=c, for not to divide by zero
            if (Math.abs(p2.getX() - q2.getX()) < epsilon) {
                // the line is in the form x=c, so the same x.
                x = p2.getX();
                // calculate the slope of this line.
                m1 = getSlope(this);
                // Solution to find the 'b' value of equation y = mx+b
                b1 = p1.getY() - (m1 * p1.getX());
                y = (m1 * x) + b1;
                return new Point(x, y);
            }
            // the general case- we need to find the intersection point of 2 equations.
            // slope of this line.
            m1 = getSlope(this);
            // slope of other line.
            m2 = getSlope(other);
            // Solution to find the 'b' value of equation y = mx+b, of the two lines.
            b1 = p1.getY() - (m1 * p1.getX());
            b2 = p2.getY() - (m2 * p2.getX());
            // now we find the 'x' and 'y' values of the intersection point by solution of equation.
            // get to this equation by subtracting two straight equations.
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
            // returning the intersection point
            return new Point(x, y);
        }

    /**.
     * function that return the slope of a line by solution.
     * @param line - a line to find his slope.
     * @return double that represent slope of line.
     */
    private double getSlope(Line line) {
        // solution of slope between 2 points.
        return ((line.start.getY() - line.end.getY())
                / (line.start.getX() - line.end.getX()));
    }
}
