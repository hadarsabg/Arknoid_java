import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Created by hadar on 31/05/2017.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;

    /**
     * constructor.
     *
     */
    public PauseScreen() {
    }

    /**
     * the logic of the animation.
     * the game is paused. if space key is pressed-
     * this. stop=true
     *
     * @param d a draw surface
     *          @param dt double dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0x989794));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText(290, d.getHeight() / 2, "-paused-", 45);
        d.drawText(280, 450, "Press space to continue ", 20);
    }

    /**
     * check if the pause mode should stop.
     *
     * @return true- if the pause should stop, else- return false.
     */
    public boolean shouldStop() {
        return false;
    }
}

