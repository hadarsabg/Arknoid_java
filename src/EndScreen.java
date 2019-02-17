import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Created by hadar on 03/06/2017.
 */


public class EndScreen implements Animation {
    private Counter lives;
    private Counter score;

    /**
     * constructor.
     *
     * @param lives -a lives counter
     * @param score - score counter
     */
    public EndScreen(Counter lives, Counter score) {
        this.lives = lives;
        this.score = score;

    }

    /**
     * the logic of the animation.
     * if player runs out of lives -game over screen.
     * else- winning screen.
     * if space key is pressed-
     * this.stop=true
     *
     * @param d  a draw surface
     * @param dt -double dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0xDFFEFF));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0xC8F8FF));
        d.fillRectangle(50, 50, 700, 500);
        d.setColor(Color.black);
        if (this.lives.getValue() == 0) {
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        }
        d.drawText(300, 450, "Press space to exit", 20);

    }

    /**
     * check if the end screen should stop.
     *
     * @return true- if the screen should stop, else- return false.
     */
    public boolean shouldStop() {
        return false;
    }
}


