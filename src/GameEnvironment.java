import java.util.ArrayList;
import java.util.List;

/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */
public class GameEnvironment {
    private List<Collidable> obstacles = new ArrayList<Collidable>();

    /**
     * add the given collidable to the environment.
     *
     * @param c - a collidable object.
     */
    public void addCollidable(Collidable c) {
        obstacles.add(c);
    }

    /**
     * remove thr given collidable from the environment.
     *
     * @param c - a collidable
     */
    public void removeCollidable(Collidable c) {
        this.obstacles.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the line of the ball movement
     * @return closest collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //if there are no obstacle in the environment
        if (obstacles.isEmpty()) {
            return null;
        }
        double minDistance;
        Point p, p2;
        Collidable c, c2;
        p = trajectory.closestIntersectionToStartOfLine(obstacles.get(0).getCollisionRectangle());
        c = obstacles.get(0);
        //if there are no collision at all
        for (int i = 1; i < obstacles.size(); i++) {
            p2 = trajectory.closestIntersectionToStartOfLine(obstacles.get(i).getCollisionRectangle());
            c2 = obstacles.get(i);
            if (p == null) {
                p = p2;
                c = c2;
            } else if (p2 != null) {
                if (p2.distance(trajectory.start()) < p.distance(trajectory.start())) {
                    p = p2;
                    c = c2;
                }
            }
        }
        if (p == null) {
            return null;
        }
        return new CollisionInfo(p, c);
    }
}


