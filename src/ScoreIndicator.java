import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Created by hadar on 26/05/2017.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor.
     *
     * @param currentScore a score counter
     */
    public ScoreIndicator(Counter currentScore) {
        this.score = currentScore;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d - a draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        String s = "Score: " + Integer.toString(this.score.getValue());
        d.drawText(350, 15, s, 20);
    }


    /**
     * interface method- return.
     *
     * @param dt double dt
     */
    public void timePassed(double dt) {
        return;
    }
}
