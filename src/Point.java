/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-04-11
 */

public class Point {
    private double x;
    private double y;

    /**
     * class constructor.
     *
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other - other point
     * @return distance of two points
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * check if points are the same.
     *
     * @param other - other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * return x value of the point.
     *
     * @return this.x
     */
    public double getX() {
        return this.x;
    }

    /**
     * return y value of the point.
     *
     * @return this.y
     */
    public double getY() {
        return this.y;
    }
}


