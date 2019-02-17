import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Image;


/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockRect;
    private int hitPoints = 1;
    private Color color = Color.gray;
    private Color strokeColor = null;
    private java.util.List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Map<Integer, Color> hitPointsToColors = new TreeMap<>();
    private Map<Integer, Image> hitPointsToImages = new TreeMap<>();

    /**
     * class constructor.
     *
     * @param rect              - the block shape - a rectangle
     * @param hitPoints         - number of hits allowed for the
     * @param hitPointsToColors maps hit points to colors of block
     * @param hitPointsToImages maps hit points to image fill of block
     * @param strokeColor       stroke color
     */
    public Block(Rectangle rect, int hitPoints,
                 Map<Integer, Color> hitPointsToColors, Map<Integer, Image> hitPointsToImages, Color strokeColor) {
        this.blockRect = rect;
        this.hitPoints = hitPoints;
        this.hitPointsToColors = hitPointsToColors;
        this.hitPointsToImages = hitPointsToImages;
        this.strokeColor = strokeColor;

    }

    /**
     * class constructor.
     * block with no maps for fill. only one color.
     *
     * @param rect      - the block shape - a rectangle
     * @param hitPoints - number of hits allowed for the
     * @param color     the color of the block
     */
    public Block(Rectangle rect, int hitPoints, Color color) {
        this.blockRect = rect;
        this.hitPoints = hitPoints;
        this.color = color;
        this.hitPointsToColors = null;
        this.hitPointsToImages = null;


    }

    /**
     * set the color of the block stroke to given color.
     *
     * @param colorStroke - a color
     */
    public void setStrokeColor(Color colorStroke) {
        this.strokeColor = colorStroke;
    }

    /**
     * set the color of the block to given color.
     *
     * @param c - color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return this.blockRect
     */
    public Rectangle getCollisionRectangle() {
        return this.blockRect;
    }

    /**
     * set the number hit points allowed.
     *
     * @param newhitPoints - number of hit points
     */
    public void setHitPoints(int newhitPoints) {
        this.hitPoints = newhitPoints;
    }

    /**
     * return the number of hit points of the block.
     *
     * @return hitPoints -number of hit points
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * // a given velocity.
     * // The return is the new velocity expected after the hit (based on
     * // the force the object inflicted on us).
     *
     * @param collisionPoint  - point of collision with rectangle
     * @param currentVelocity - balls velocity
     * @param hitter          - the ball that hits this block
     * @return new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if hits corners
        if (collisionPoint.equals(blockRect.getLowerLeft()) || collisionPoint.equals(blockRect.getLowerRight())
                || collisionPoint.equals(blockRect.getUpperLeft())
                || collisionPoint.equals(blockRect.getUpperRight())) {
            this.setHitPoints(--this.hitPoints);
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
        //if hits upper or lower side
        if (collisionPoint.getY() == blockRect.getUpperLeft().getY()
                || collisionPoint.getY() == blockRect.getLowerLeft().getY()) {
            this.setHitPoints(--this.hitPoints);
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
        //if hits left or right side
        if (collisionPoint.getX() == blockRect.getUpperLeft().getX()
                || collisionPoint.getX() == blockRect.getUpperRight().getX()) {
            this.setHitPoints(--this.hitPoints);
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        //if doesn't hit block
        return currentVelocity;
    }

    /**
     * return color of block.
     *
     * @return this.color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * draw the block on the given DrawSurface.
     *
     * @param surface -a draw surface
     */
    public void drawOn(biuoop.DrawSurface surface) {
        int currentHitPoints = this.getHitPoints();
        if (this.hitPointsToColors != null) {
            if (this.hitPointsToColors.get(currentHitPoints) != null) {
                surface.setColor(this.hitPointsToColors.get(currentHitPoints));
                surface.fillRectangle((int) this.blockRect.getUpperLeft().getX(),
                        (int) this.blockRect.getUpperLeft().getY(),
                        (int) this.blockRect.getWidth(), (int) this.blockRect.getHeight());
            }
        }
        if (this.hitPointsToImages != null) {
            if (this.hitPointsToImages.get(currentHitPoints) != null) {
                surface.drawImage((int) this.blockRect.getUpperLeft().getX(),
                        (int) this.blockRect.getUpperLeft().getY(), this.hitPointsToImages.get(currentHitPoints));
            }
        }
        if (this.hitPointsToImages == null || this.hitPointsToColors == null) {
            surface.setColor(this.getColor());
            surface.fillRectangle((int) this.blockRect.getUpperLeft().getX(),
                    (int) this.blockRect.getUpperLeft().getY(),
                    (int) this.blockRect.getWidth(), (int) this.blockRect.getHeight());
        }
        if (this.strokeColor != null) {
            surface.setColor(this.strokeColor);
            surface.drawRectangle((int) this.blockRect.getUpperLeft().getX(),
                    (int) this.blockRect.getUpperLeft().getY(),
                    (int) this.blockRect.getWidth(), (int) this.blockRect.getHeight());
        }
    }


    /**
     * interface method- return.
     *
     * @param dt double dt.
     */

    public void timePassed(double dt) {
        return;
    }

    /**
     * add the block to the game as a collidable and as asprite.
     *
     * @param game - a game object
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprit(this);
    }

    /**
     * remove the block to the game as a collidable and as asprite.
     *
     * @param game a game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);

    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hit Listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }


    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hit Listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify all hit listener of this block about the hit event.
     *
     * @param hitter - the ball that hits this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        java.util.List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


}

