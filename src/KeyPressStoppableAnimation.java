import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by hadar on 14/06/2017.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decoratedAnimation;
    private KeyboardSensor keyboardSensor;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * constructor.
     *
     * @param sensor    KeyboardSensor
     * @param key       - key
     * @param animation animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.decoratedAnimation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.decoratedAnimation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }

    }


    @Override
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            return true;
        }
        return this.stop;
    }

}
