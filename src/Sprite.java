import biuoop.DrawSurface;

/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */

public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d - a draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt double dt
     */
    void timePassed(double dt);
}

