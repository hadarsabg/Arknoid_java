import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by hadar on 14/06/2017.
 *
 * @param <T> the type of the tasks
 */
public class MenuAnimation<T> implements Menu {
    private Map<String, Object> optionalTasks = new TreeMap<String, Object>();
    private List<String> messages = new ArrayList<String>();
    private String menuTitle;
    private KeyboardSensor keyboardSensor;
    private boolean stop = false;
    private Object status;


    /**
     * constructor.
     *
     * @param menuTitle      a string for the title
     * @param keyboardSensor keyboardsensor
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboardSensor) {
        this.menuTitle = menuTitle;
        this.keyboardSensor = keyboardSensor;


    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        int blockWidth = 50;
        int blockHeight = 35;
        java.util.List<Color> colors = new ArrayList<>();
        colors.add(Color.cyan);
        colors.add(Color.yellow);
        colors.add(Color.orange);
        colors.add(Color.green);
        colors.add(Color.MAGENTA);

        int hits = 2;
        int row = 7;
        for (int j = 1; j <= 5; j++) {
            Color c = colors.get(j - 1);
            for (int i = 1; i < row; i++) {
                if (!((i == 4 && j == 1) || (j == 4 && i == 8) || (j == 3 && i == 8))) {
                    d.setColor(c);
                    d.fillRectangle(800 - (i * blockWidth), 390 + (j * blockHeight), blockWidth, blockHeight);
                    d.setColor(Color.black);
                    d.drawRectangle(800 - (i * blockWidth), 390 + (j * blockHeight), blockWidth, blockHeight);
                }
            }
            row++;
        }

        d.setColor(Color.magenta);
        d.drawText(300, 100, this.menuTitle, 55);
        d.setColor(new Color(0xF8CFB4));
        d.fillCircle(600, 200, 9);
        d.fillCircle(100, 100, 9);
        for (int i = 0; i < this.messages.size(); i++) {
            d.drawText(100, 250 + (i * 50), this.messages.get(i), 25);
        }
        Object[] keys = this.optionalTasks.keySet().toArray();
        for (int j = 0; j < keys.length; j++) {
            if (keyboardSensor.isPressed((String) keys[j])) {
                this.status = optionalTasks.get(keys[j]);
                this.stop = true;
            }
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

    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.optionalTasks.put(key, returnVal);
        this.messages.add(message);
    }

    @Override
    public Object getStatus() {
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        return;
    }

}





