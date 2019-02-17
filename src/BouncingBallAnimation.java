import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-04-11
 */
public class BouncingBallAnimation {
    /**
     * draw a moving ball on a draw surface.
     * create a new  ball and gui objects , draw the ball in different
     * position according to ball's velocity in 50 milliseconds gap
     *
     * @param args - strings array
     */
    public static void main(String[] args) {
        GameEnvironment gameEnvironment = new GameEnvironment();
        GUI gui = new GUI("ball", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(59, 100, 30, java.awt.Color.BLACK, gameEnvironment);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep(1.0 / 60);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}