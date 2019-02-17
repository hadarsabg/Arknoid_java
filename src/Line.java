import java.util.List;

/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-04-11
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * class constructor.
     *
     * @param end   - ending point of line
     * @param start - star point of line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * class constructor.
     *
     * @param x1 - x coordinate of the start point
     * @param y1 - y coordinate of the start point
     * @param x2 - x coordinate of the end point
     * @param y2 - x coordinate of the end point
     */

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return distance between start and end point
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return middle point
     */
    public Point middle() {
        double midx = (this.end.getX() + this.start.getX()) / 2;
        double midy = (this.end.getY() + this.start.getY()) / 2;
        return new Point(midx, midy);
    }

    /**
     * Returns the start point of the line.
     *
     * @return start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the intersection point of two lines.
     *
     * @param other - other line
     * @return if exist- return intersection point of the lines,
     * else- return null
     */
    public Point getIntersectionPoint(Line other) {
        double intersectX, intersectY;
        if (this.start().getX() == this.end().getX()) {
            if (other.start().getX() == other.end().getX()) {
                return null;
            } else {
                double m2 = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
                double b2 = other.start.getY() - (m2 * other.start.getX());
                intersectX = this.start().getX();
                intersectY = m2 * intersectX + b2;
            }
        } else {
            double m1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double b1 = this.start.getY() - (m1 * this.start.getX());
            if (other.start().getX() == other.end().getX()) {
                intersectX = other.start().getX();
                intersectY = m1 * intersectX + b1;
            } else {
                double m2 = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
                if (m1 == m2) {
                    return null;
                } else {
                    double b2 = other.start.getY() - (m2 * other.start.getX());
                    intersectX = (b2 - b1) / (m1 - m2);
                    intersectY = m1 * intersectX + b1;
                }
            }
        }
        Point resultPoint = new Point(Math.round(intersectX * 1000.0) / 1000.0,
                Math.round(intersectY * 1000.0) / 1000.0);
        // check if intersection is in the range of the two lines
        if (intersectX <= Math.max(this.start.getX(), this.end.getX())
                && intersectX >= Math.min(this.start.getX(), this.end.getX())
                && intersectY <= Math.max(this.start.getY(), this.end.getY())
                && intersectY >= Math.min(this.start.getY(), this.end.getY())
                && intersectX <= Math.max(other.start.getX(), other.end.getX())
                && intersectX >= Math.min(other.start.getX(), other.end.getX())
                && intersectY <= Math.max(other.start.getY(), other.end.getY())
                && intersectY >= Math.min(other.start.getY(), other.end.getY())) {
            return resultPoint;
        }
        return null;
    }

    /**
     * check if two lines intersect.
     *
     * @param other - other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        if (this.getIntersectionPoint(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * return intersection point of two lines.
     *
     * @param other - other line
     * @return this.getIntersectionPoint(other)
     */
    public Point intersectionWith(Line other) {
        return (this.getIntersectionPoint(other));
    }

    /**
     * check if lines are the same.
     *
     * @param other - other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start()) && this.end.equals(other.end());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect - a rectangle
     * @return closest intersection point of line with rect
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int i;
        Point p = null;
        List<Point> lst = rect.intersectionPoints(this);
        if (lst.isEmpty()) {
            return null;
        }
        if (lst.size() == 1) {
            return lst.get(0);
        }
        double d0 = this.start().distance(lst.get(0));
        double d1 = this.start().distance(lst.get(1));

        if (d0 >= d1) {
            return lst.get(1);
        } else {
            return lst.get(0);
        }

    }
}

