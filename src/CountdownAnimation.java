import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Created by hadar on 31/05/2017.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;


    /**
     * constructor.
     *
     * @param numOfSeconds the pause between each number
     * @param countFrom    the number that starts the countdown
     * @param gameScreen   the spites collection of the game.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * the animation logic.
     * draw all sprites of the game,
     * and prints the count down.
     * updeted the stop condition when count reaches 0.
     *
     * @param d  draw surface
     * @param dt -double dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 70);
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor((long) ((this.numOfSeconds / 3) * 1000));
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.countFrom--;
    }

    /**
     * deter if the animation needs to stop.
     *
     * @return this.stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

