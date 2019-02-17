import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRect;
    private GameLevel game;
    private int rightBorder;
    private int leftBorder;
    private Color color;
    private int speed;


    /**
     * class constructor.
     *
     * @param rectangle   - the paddle shape
     * @param game        - a game object
     * @param rightBorder - the game surface left border
     * @param leftBorder  - the game surface right border
     * @param runner      - animation runner
     * @param color       - the color of the paddle
     * @param speed       - the paddle speed
     */
    public Paddle(Rectangle rectangle, GameLevel game, int rightBorder, int leftBorder,
                  AnimationRunner runner, Color color, int speed) {
        this.paddleRect = rectangle;
        this.game = game;
        this.keyboard = runner.getGui().getKeyboardSensor();
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.color = color;
        this.speed = speed;
    }

    /**
     * if paddle dosent exit left border move its location to the left.
     * @param dt double dt
     */
    public void moveLeft(double dt) {
        if (this.paddleRect.getUpperLeft().getX() >= this.leftBorder) {
            Point newLocation =
                    new Point(this.paddleRect.getUpperLeft().getX() - speed * dt,
                            this.paddleRect.getUpperLeft().getY());
            this.paddleRect = new Rectangle(newLocation, this.paddleRect.getWidth(), this.paddleRect.getHeight());
        }
    }

    /**
     * if paddle dosent exit right border move its location to the right.
     * @param dt double dt
     */
    public void moveRight(double dt) {
        if (this.paddleRect.getUpperRight().getX() <= this.rightBorder) {
            Point newLocation =
                    new Point(this.paddleRect.getUpperLeft().getX() + speed * dt,
                            this.paddleRect.getUpperLeft().getY());
            this.paddleRect = new Rectangle(newLocation, this.paddleRect.getWidth(), this.paddleRect.getHeight());
        }
    }

    /**
     * sprite method - move left or right according to keyboard arrows.
     * @param dt double dt
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * draw the paddle on a given surface.
     *
     * @param d - adraw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddleRect.getUpperLeft().getX(), (int) this.paddleRect.getUpperLeft().getY(),
                (int) this.paddleRect.getWidth(), (int) this.paddleRect.getHeight());
    }

    // Collidable

    /**
     * return the paddle rectangle.
     *
     * @return the paddle rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRect;
    }

    /**
     * return the new ball velocity after the ball hit paddle.
     *
     * @param collisionPoint  - collision point with the collidable shape
     * @param currentVelocity - the ball velocity
     * @param hitter          - the ball that is doing the hit
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionWidth = this.paddleRect.getWidth() / 5;
        double upperLeftX = this.paddleRect.getUpperLeft().getX();
        //if hits upper side
        if (collisionPoint.getY() == paddleRect.getUpperLeft().getY()) {
            //if hits 1st region
            if (collisionPoint.getX() >= upperLeftX && collisionPoint.getX() <= upperLeftX + regionWidth) {
                Velocity v = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                return v;
                //if hits 2nd region
            } else if (collisionPoint.getX() >= upperLeftX + regionWidth
                    && collisionPoint.getX() <= upperLeftX + (2 * regionWidth)) {
                Velocity v = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                return v;
                //if hits 3 region
            } else if (collisionPoint.getX() >= upperLeftX + (2 * regionWidth)
                    && collisionPoint.getX() <= upperLeftX + (3 * regionWidth)) {
                Velocity v = new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
                return v;
                //4 region
            } else if (collisionPoint.getX() >= upperLeftX + (3 * regionWidth)
                    && collisionPoint.getX() <= upperLeftX + (4 * regionWidth)) {
                Velocity v = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                return v;
                //5 region
            } else if (collisionPoint.getX() >= upperLeftX + (4 * regionWidth)
                    && collisionPoint.getX() <= upperLeftX + (5 * regionWidth)) {
                Velocity v = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                return v;
            }
        }
        //if hits lower side
        if (collisionPoint.getY() == paddleRect.getLowerLeft().getY()) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
        //if hits left or right side
        if (collisionPoint.getX() == paddleRect.getUpperLeft().getX()
                || collisionPoint.getX() == paddleRect.getUpperRight().getX()) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        //if doesn't hit paddle
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - a game object
     */
    public void addToGame(GameLevel g) {
        g.addSprit(this);
        g.addCollidable(this);
    }

    /**
     * remove this paddle from  game.
     *
     * @param g - a game object
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * move paddle to center point.
     *
     * @param center - the center point of the surface
     */
    public void moveToCenter(Point center) {
        this.paddleRect = new Rectangle(center, this.paddleRect.getWidth(), this.paddleRect.getHeight());
    }


}
