/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return - a rectangle
     */
    Rectangle getCollisionRectangle();

    //

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  - collishion point with the collidable shape
     * @param currentVelocity - the ball velocity
     * @param hitter          -the ball that hits the block
     * @return - the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * @param drawSurface - a draw surface
     */
    void drawOn(biuoop.DrawSurface drawSurface);
}
