import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Created by hadar on 27/05/2017.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * constructor.
     *
     * @param currentLives a life counter
     */
    public LivesIndicator(Counter currentLives) {
        this.lives = currentLives;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d - a draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        String s = "Lives: " + Integer.toString(this.lives.getValue());
        d.drawText(80, 15, s, 20);
    }


    /**
     * interface method- return.
     * @param dt double dt
     */
    public void timePassed(double dt) {
        return;
    }
}

