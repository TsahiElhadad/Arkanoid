/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;

/**.
 * game.Ball class.
 * The class will represent a game.Ball, with his features: center point, radius, color and velocity.
 */
public class Ball implements Sprite {
    // center point of the ball.
    private Point center;
    // radius of the ball.
    private int radius;
    // color of the ball.
    private java.awt.Color color;
    // velocity of the ball.
    private Velocity vel;
    // values of the width and hight values screen.
    private static final int WIDHT = 400;
    private static final int HIGHT = 400;
    // game envirment for the ball.
    private GameEnvironment gE;

    /**
     * .
     * Constructor of game.Ball, create ball with his radius, center point and color.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.vel = new Velocity(0, 0);
        this.gE = null;
    }

    /**
     * .
     * Constructor of game.Ball, create ball with x,y values of the center point, radius and color.
     *
     * @param x      - x value of the center point.
     * @param y      - y value of the center point.
     * @param radius - radius value of the ball.
     * @param color  - color of the ball.
     */
    public Ball(int x, int y, int radius, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.vel = new Velocity(0, 0);
        this.gE = null;
    }

    /**
     * .
     * Function that return the x values of the center point
     *
     * @return int value x center point
     */
    public int getX() {
        return (int) (this.center.getX());
    }

    /**
     * .
     * Function that return the y values of the center point
     *
     * @return int value y center point
     */
    public int getY() {
        return (int) (this.center.getY());
    }

    /**
     * .
     * Function that return the radius of game.Ball.
     *
     * @return the radius of game.Ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * .
     * Function that return the color of the ball.
     *
     * @return java.awt.Color type that represent the color ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    /**.
     * .
     * Function that draw the ball on the given DrawSurface.
     *
     * @param surface DrawSurface object.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.getX(), (int) this.getY(), this.radius);
    }

    /**
     * .
     * Function that notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Function that adding the ball to the game, ball is sprite.
     * @param g - get game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**.
     * Function that set the velocity of the ball.
     *
     * @param v - get velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * Function that set the velocity of the ball.
     *
     * @param dx - dx value of velocity.
     * @param dy - dy value of velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * Function that return the velocity of the ball.
     *
     * @return - the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**.
     * function to move one step for the MltipleBouncingBallsAnimation, use function overloading
     * to adjust the movement of the balls to the request and size of the rectangles.
     * function that get the limits of the rectangle and save from the ball to move inside
     * the borders.
     *
     * @param width      - the width of the form(by x value).
     * @param leftBorder - the value of the left border(by x values).
     * @param topBorder  - the value of the top border(by y value).
     * @param downBorder - the value of the bottom border(by y value).
     */
    public void moveOneStep(int width, int leftBorder, int topBorder, int downBorder) {
        // if we are too much right and the velocitiy is +.
        if ((this.center.getX() + vel.getDx() + this.radius) > width
                && this.vel.getDx() >= 0) { // in the range of the top corner.
            // change the dx direction.
            this.setVelocity(-this.vel.getDx(), this.vel.getDy());
        } // if the left corner is outside the screen and the velocitiy is -.
        if ((this.center.getX() - this.radius + vel.getDx()) < leftBorder
                && this.vel.getDx() <= 0) {
            // change the dx direction to .
            this.setVelocity(-this.vel.getDx(), this.vel.getDy());
        }
        // check if the move will hit the high border of the screen and the velocity is -.
        if ((this.center.getY() - this.radius + this.vel.getDy() < topBorder)
                && (this.vel.getDy() <= 0)) {
            double dy = -this.vel.getDy();
            this.setVelocity(this.vel.getDx(), dy);
        }
        // check if the move will hit the low border of the screen and the velocity is +.
        if ((this.center.getY() + this.radius + this.vel.getDy() > downBorder)
                && (this.vel.getDy() >= 0)) {
            double dy = -this.vel.getDy();
            this.setVelocity(this.vel.getDx(), dy);
        }
        // set the center point to his new location.
        this.center = vel.applyToPoint(this.center);
    }

    /**
     * Function that initial game.Game environment for the ball.
     * @param gEn - the game envirment.
     */
    public void setGameEnvironment(GameEnvironment gEn) {
        this.gE = gEn;
    }

    /**.
     * Function that return game enviroment.
     * @return - this game enviroment.
     */
    public GameEnvironment getGe() {
        return this.gE;
    }

    /**.
     * Function that moving the ball according to the collidable objects in the game,
     * make the trajectory of the ball, from his center point, and the ending point
     * will be the point after applyToPoint to increase the range check for returning
     * from the hit. check the trajectory until he hit collidable and change the velocity
     * with function hit we created.
     */
    public void moveOneStep() {
        // the closest collision point of the ball trajectory.
        Point collisionPoint;
        // the closest object the ball hit.
        Collidable object;
        // the new velocity we need to return.
        Velocity v;
        // the ball after move one step by his velocity, to increase the range check.
        Point pAfterMove = this.vel.applyToPoint(this.center);
        // we assume that the center ball hits the surface, so the trajectory of the ball.
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                pAfterMove.getX() + this.vel.getDx(), pAfterMove.getY() + this.vel.getDy());
        // get closest collision point with trajectory and the colliadble in the game.
        CollisionInfo colInfo;
        colInfo = gE.getClosestCollision(trajectory);
        // if the trajectory is not clear and there is obstacle acrros the path.
        if (colInfo != null) {
            // get the collision point and the object we colliade with.
            collisionPoint = colInfo.collisionPoint();
            object = colInfo.collisionObject();
            // get the new velocity according to the collision point.
            v = object.hit(this, collisionPoint, this.vel);
            // set the new velocity we get.
            this.setVelocity(v);
        }
        // change the center point.
        this.center = vel.applyToPoint(this.center);
    }

    /**
     * Function that removing ball from the game.
     * @param g - game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
