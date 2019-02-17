import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Created by hadar on 30/05/2017.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     */
    public AnimationRunner() {
        this.gui = new GUI("arkanoid", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * run the animation do one frame while animation not should stop.
     *
     * @param animation animation object
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            //double dt = 1.0 / this.framesPerSecond;
            animation.doOneFrame(d, 1.0 / this.framesPerSecond);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * return this gui.
     *
     * @return this.gui
     */
    public GUI getGui() {
        return this.gui;
    }
}

