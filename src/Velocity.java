/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-04-11
 */

// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * class constructor.
     *
     * @param dx - change on x axis
     * @param dy - change on y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * return change on x axis.
     *
     * @return this.dx -change on x axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * return change on y axis.
     *
     * @return this.dy -change on y axis
     */
    public double getDy() {
        return this.dy;

    }

    /**
     * update a point coordinates.
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p - point
     * @return point (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    /**
     * Converting from polar to Cartesian coordinates.
     *
     * @param angle - angle of move
     * @param speed - double
     * @return v -velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = (-1) * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * return the angle of the velocity.
     * @return angle of velocity.
     */
    public double getAngle() {
        return this.dy / this.dx;
    }

    /**
     * return the speed vector of the velocity.
     * @return speed vector.
     */
    public double getSpeed() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }


}



