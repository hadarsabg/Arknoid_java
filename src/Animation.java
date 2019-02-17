import biuoop.DrawSurface;

/**
 * Created by hadar on 30/05/2017.
 */
public interface Animation {
    /**
     * the animation logic.
     *
     * @param d  draw surface
     * @param dt - double dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * check the conditions to stop the animation.
     *
     * @return true if the animation shold stop, else -return false
     */
    boolean shouldStop();
}

