/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;

/**.
 * game.Paddle class.
 * Class for the paddle of the game.
 */
public class Paddle implements Sprite, Collidable {
    // the paddle rectangle.
    private Rectangle rectangle;
    // keyboard sensor to move the paddle.
    private biuoop.KeyboardSensor keyboard;
    // the color of the paddle.
    private Color color;
    // seed paddle
    private int speedPaddle;
    // paddle width
    private int paddleWidth;
    // the borders of the screen
    private static final int LEFT_BORDER = 25;
    private static final int RIGHT_BORDER = 800;
    // variable for jump of paddale.
    private static final int PADDLE_JUMP = 6;

    /**.
     * Constructor for the game.Paddle.
     * @param rec - get rectangle.
     * @param color - get color of paddle.
     * @param keyboard - get keyboard sensor.
     */
    public Paddle(Rectangle rec, Color color, biuoop.KeyboardSensor keyboard) {
        this.rectangle = rec;
        this.color = color;
        this.keyboard = keyboard;
        this.speedPaddle = PADDLE_JUMP;
    }

    /**.
     * Another Constructor for Paddle.
     * @param rec - get rectangle.
     * @param color - get color of paddle.
     * @param keyboard - get keyboard sensor.
     * @param speedPaddle - paddle speed.
     * @param paddleWidth - paddle width.
     */
    public Paddle(Rectangle rec, Color color, biuoop.KeyboardSensor keyboard, int speedPaddle,
                  int paddleWidth) {
        this.rectangle = rec;
        this.color = color;
        this.keyboard = keyboard;
        this.speedPaddle = speedPaddle;
        this.paddleWidth = paddleWidth;
    }

    /**.
     * Function that move the paddle left according to user request.
     */
    public void moveLeft() {
        // check if the move will not cross the left border.
        if (this.rectangle.getUpperLeft().getX() - this.speedPaddle >= LEFT_BORDER) {
            // create new point for the paddle according to move left.
            Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX()
                    - this.speedPaddle, this.rectangle.getUpperLeft().getY());
            this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(),
                    this.rectangle.getHeight());
        }
    }

    /**
     * Function that move the paddle right according to user request.
     */
    public void moveRight() {
        // check if the move will not cross the right border.
        if (this.rectangle.getUpperLeft().getX() + this.paddleWidth + this.speedPaddle
                <= (RIGHT_BORDER - LEFT_BORDER)) {
            // create new point for the paddle according to move right.
            Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX()
                    + this.speedPaddle, this.rectangle.getUpperLeft().getY());
            this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(),
                    this.rectangle.getHeight());
        }
    }

    /**
     * Function that draw the sprite to the screen.
     * implement from sprite interface.
     * @param surface - the DrawsUrafe object.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // set the paddale color.
        surface.setColor(this.color);
        // draw the rectangle.
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
        // draw the corners of the rectangle in black.
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
    }

    /**.
     * Function that notify if the user press right or left in the keyboard.
     * implement from sprite interface.
     */
    @Override
    public void timePassed() {
        // if the user press left KEY or "a" that mean left.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                || keyboard.isPressed("a")) {
            moveLeft();
         }
        // if the user press right KEY or "d" that mean right.
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                || keyboard.isPressed("d")) {
            moveRight();
        }
    }

    /**.
     * Function that return the rectangle paddle.
     * @return - return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Function that check where the collision point is in the paddle and
     * return a new vellocity acoording to the location of the collision point in the paddle.
     * The behavior of the ball's bounce depends on where it hits the paddle.
     * sepearate to regions, denote the left-most region as 1 and the rightmost as 5
     * (so the middle region is 3).
     * @param collisionPoint - the collision point.
     * @param currentVelocity - the cuurent vellocity of the object.
     * @param hitter - the ball hitter.
     * @return - new velocity for the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double upperLeft = this.rectangle.getUpperLeft().getX();
        // we divide the width of the paddle to 5 areas.
        double regionsByWidth = this.rectangle.getWidth() / 5;
        // supremum for regin 1
        double supReg1 = upperLeft + regionsByWidth;
        // supremum for regin 2
        double supReg2 = upperLeft + (2 * regionsByWidth);
        // supremum for regin 3
        double supReg3 = upperLeft + (3 * regionsByWidth);
        // supremum for regin 4
        double supReg4 = upperLeft + (4 * regionsByWidth);
        // supremum for regin 5
        double supReg5 = upperLeft + (5 * regionsByWidth);

        Velocity vel = currentVelocity;
        // check if the collision is in region 1. 300 degrees for this region, same speed.
        if (collisionPoint.getX() >= upperLeft && collisionPoint.getX() <= supReg1) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        // check if the collision is in region 2. 330 degrees for this region, same speed.
        if (collisionPoint.getX() >= supReg1 && collisionPoint.getX() <= supReg2) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        // check if the collision is in region 4. 30 degrees for this region, same speed.
        if (collisionPoint.getX() >= supReg3 && collisionPoint.getX() <= supReg4) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        // check if the collision is in region 5. 60 degrees for this region, same speed.
        if (collisionPoint.getX() >= supReg4 && collisionPoint.getX() <= supReg5) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        // check if the collision is in region 3. change only the dy.
        if (collisionPoint.getX() >= supReg2 && collisionPoint.getX() <= supReg3) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // if the collision is in left side of paddle.
        if (collisionPoint.getX() <= upperLeft) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // if the collision is in right side of paddle.
        if (collisionPoint.getX() >= (upperLeft + this.rectangle.getWidth())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return vel;
    }

    /**
     * Function that add this paddale to the game, paddle is also interfaces.Sprite and coalidable.
     * @param g - the game.Game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Function that remove paddle from game.Sprite and coalidable.
     * @param g - the game.Game object.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}
