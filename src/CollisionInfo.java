/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * class constructor.
     * @param collisionPoint - point of collision with colidable
     * @param collisionObject - a collidable object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint.getX() ,  collisionPoint.getY());
        this.collisionObject = collisionObject;
    }
    /**
     * the point at which the collision occurs.
     * @return this.collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * the collidable object involved in the collision.
     * @return this.collisionObject
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    }


