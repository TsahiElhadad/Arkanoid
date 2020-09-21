/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * game.Block class.
 * The class will represent a block in the game, which build from a rectangle
 * and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // the rectangle block.
    private Rectangle block;
    // the color of block.
    private Color color;
    // list of hitListeners
    private List<HitListener> hitListeners;

    /**
     * Constructor for game.Block.
     * @param rec - get Geometry.Rectangle.
     * @param color - get Color of game.Block.
     */
    public Block(Rectangle rec, Color color) {
        this.block = rec;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**.
     * Function that draw the sprite to the screen.
     * @param surface - get a Drawsurface object.
     */
    public void drawOn(DrawSurface surface) {
        // draw inside the rectangle.
        surface.setColor(this.color);
        surface.fillRectangle((int) block.getUpperLeft().getX(), (int) block.getUpperLeft().getY(),
                (int) block.getWidth(),
                (int) block.getHeight());
        // draw rectangle borders.
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) block.getUpperLeft().getX(), (int) block.getUpperLeft().getY(),
                (int) block.getWidth(),
                (int) block.getHeight());
    }

    @Override
    /**
     * Function that adding the block to the game, block is collidable and sprite.
     * @param g - get game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * .
     * Function that notify the sprite that time has passed. does nothing now.
     */
    @Override
    public void timePassed() {
    }

    @Override
    /**
     * Function that return the block rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    /**
     * Function that get collision point with this block and current velocity and
     * calculate where is the collision point accure - on top or down lines of the block
     * or in the left and right lines of block. and according to that change the velocity and
     * return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // notify Hit.
        this.notifyHit(hitter);
        // epsilon for accurecy in the check.
        double epsilon = 0.1;
        // the new game.Velocity that need to return.
        Velocity newVel = currentVelocity;
        // if collisiion point is null.
        if (collisionPoint == null) {
            return currentVelocity;
        }
        // get the left bottom point of the block.
        Point pLeftDown = this.block.getLeftLine().intersectionWith(this.block.getBottomLine());
        // get the right up point of the block.
        Point pRightUp = this.block.getUpperLine().intersectionWith(this.block.getRightLine());
        // get the right bottom point of the block.
        Point pRightDown = this.block.getRightLine().intersectionWith(this.block.getBottomLine());
        // check if the collision point is in the edges of the block.
        // if the points are not null.
        if (pLeftDown != null && pRightDown != null && pRightUp != null
                && this.block.getUpperLeft() != null) {
            if (this.block.getUpperLeft().equals(collisionPoint) || collisionPoint.equals(pLeftDown)
                    || collisionPoint.equals(pRightUp) || collisionPoint.equals(pRightDown)) {
                newVel = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
                return newVel;
            }
        }
        // if the collision point is in the sides(left or right) of the rectangle.
        if (Math.abs(collisionPoint.getX() - block.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX() - (block.getUpperLeft().getX()
                    + block.getWidth())) < epsilon) {
            // change velocity of dx value of velocity to -.
            newVel = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // if the collision is in the up or down of the block.
        if (Math.abs(collisionPoint.getY() - block.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - (block.getUpperLeft().getY() + block.getHeight())) < epsilon) {
            // change velocity of dy value of velocity to -.
            newVel = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return newVel;
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl - listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * remove hl listener from the hitlistener list.
     * @param hl - listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Function that notify all losteners a bout the hit.
     * @param hitter - ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Function that removing the block from the game.
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
