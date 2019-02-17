import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadar on 29/04/2017.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft - the upper left pint
     * @param width     - widht of rectangle
     * @param height    - height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * // with the specified line.
     *
     * @param line - a line
     * @return list of intersection points of line with rectangle
     */
    public List intersectionPoints(Line line) {
        Line up = new Line(this.getUpperRight(), this.getUpperLeft());
        Line down = new Line(this.getLowerRight(), this.getLowerLeft());
        Line left = new Line(this.getUpperLeft(), this.getLowerLeft());
        Line right = new Line(this.getUpperRight(), this.getLowerRight());
        List<Point> intersections = new ArrayList<Point>();
        if (up.isIntersecting(line)) {
            intersections.add(up.intersectionWith(line));
        }
        if (down.isIntersecting(line)) {
            intersections.add(down.intersectionWith(line));
        }
        if (left.isIntersecting(line)) {
            intersections.add(left.intersectionWith(line));
        }
        if (right.isIntersecting(line)) {
            intersections.add(right.intersectionWith(line));
        }
        return intersections;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return this.width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return this.height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-right point of the rectangle.
     *
     * @return upper right point
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
    }

    /**
     * Returns the lower-left point of the rectangle.
     *
     * @return lower left point
     */
    public Point getLowerLeft() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * Returns the lower right point of the rectangle.
     *
     * @return lower right point
     */
    public Point getLowerRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());

    }

}
