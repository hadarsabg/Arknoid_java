import java.awt.Color;

/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-04-11
 */

public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private int upperBound;
    private int rightBound;
    private int leftBound;
    private int bottomBound;
    private GameEnvironment environment;


    /**
     * class constructor.
     *
     * @param center      - the ball center point
     * @param r           - the ball radius
     * @param color       - the color of the ball
     * @param environment - the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.environment = environment;
        this.setVelocity(0, 0);
    }

    /**
     * class constructor.
     *
     * @param x           - x coordinate of the ball center
     * @param y           - y coordinate of the ball center
     * @param r           - the ball radius
     * @param color       - the ball color
     * @param environment -the game environment
     */

    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.environment = environment;
        this.setVelocity(0, 0);
    }

    /**
     * return the ball center x coordinate.
     *
     * @return int the ball center x coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * return the ball center y coordinate.
     *
     * @return int the ball center y coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return the ball radius.
     *
     * @return r - the ball radius
     */
    public int getSize() {
        return r;
    }

    /**
     * return the ball color.
     *
     * @return this.color - the ball radius
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface -a draw surface
     */
    public void drawOn(biuoop.DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * set ball velocity to v.
     *
     * @param v - velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set ball velocity using dx and dy.
     *
     * @param dx - change in x axis
     * @param dy - change in y axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * set ball velocity using dx and dy.
     *
     * @return this.velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set ball's frame borders.
     *
     * @param up    - ball upper Bound
     * @param down  - bottom Bound
     * @param left  - left border
     * @param right - right border
     */
    public void setBorder(int up, int down, int left, int right) {
        this.upperBound = up;
        this.rightBound = right;
        this.leftBound = left;
        this.bottomBound = down;
    }

    /**
     * return game environment.
     *
     * @return this.environment -the ball game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * change the ball position.
     *
     *@param dt  -double dt
     */
    public void moveOneStep(double dt) {
        Velocity v1 = new Velocity(this.getVelocity().getDx() * dt, this.getVelocity().getDy() * dt);
        Line trajectory = new Line(this.center, v1.applyToPoint(this.center));
        // Line trajectory = new Line(this.center,
        //  new Point(this.center.getX() + this.getVelocity().getDx(),
        //        this.center.getY() + this.getVelocity().getDy()));
        CollisionInfo collision = this.getEnvironment().getClosestCollision(trajectory);
        if (collision == null) {
            this.center = v1.applyToPoint(this.center);
            return;
        }
        Rectangle rectangle = collision.collisionObject().getCollisionRectangle();


        //if hits corners of block

        if (collision.collisionPoint().equals(rectangle.getUpperRight())
                || collision.collisionPoint().equals(rectangle.getUpperLeft())
                || collision.collisionPoint().equals(rectangle.getLowerRight())
                || rectangle.getUpperLeft().equals(collision.collisionPoint())) {
            Velocity v = collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity());
            this.setVelocity(v);
            return;
        }
        // if hits upper line
        if (collision.collisionPoint().getY() == rectangle.getUpperLeft().getY()) {
            this.center = new Point(collision.collisionPoint().getX(),
                    collision.collisionPoint().getY() - this.getSize());
            Velocity v = collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity());
            this.setVelocity(v);
            return;

        }
        //if hits lower line
        if (collision.collisionPoint().getY() == rectangle.getLowerRight().getY()) {
            this.center = new Point(collision.collisionPoint().getX(),
                    collision.collisionPoint().getY() + this.getSize());
            Velocity v = collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity());
            this.setVelocity(v);
            return;

        }
        //if hits right side
        if (collision.collisionPoint().getX() == rectangle.getUpperRight().getX()) {
            this.center = new Point(collision.collisionPoint().getX() + this.getSize(),
                    collision.collisionPoint().getY());
            Velocity v = collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity());
            this.setVelocity(v);
            return;

        }
        //if hits left side
        if (collision.collisionPoint().getX() == rectangle.getUpperLeft().getX()) {
            this.center = new Point(collision.collisionPoint().getX() - this.getSize()
                    , collision.collisionPoint().getY());
            ;
            Velocity v = collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity());
            this.setVelocity(v);
            return;

        }

    }

    /**
     * return the ball speed.
     *
     * @return 100/ ball radius - if radius is less then 50, else 2
     */
    public double getBallSpeed() {
        if (this.getSize() >= 50) {
            return 2;
        }
        return 100 / this.getSize();
    }

    /**
     * call the moveOneStep method.
     * @param dt double dt
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * add the ball to the sprite collection of the game.
     *
     * @param game - a given game object.
     */
    public void addToGame(GameLevel game) {
        game.addSprit(this);
    }

    /**
     * remove the ball from the sprite collection of the game.
     *
     * @param game a given game object
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}


