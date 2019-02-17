import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */
public class SpriteCollection {

    private List<Sprite> spritesList = new ArrayList<>();

    /**
     * a sprite to the list.
     *
     * @param s - a sprite object.
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * remove a given spite from the list.
     *
     * @param s - a sprite
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param dt double dt
     */
    public void notifyAllTimePassed(double dt) {
        // List<Sprite> spritesList1 = new ArrayList<Sprite>(this.spritesList);
        for (int i = 0; i < this.spritesList.size(); i++) {
            spritesList.get(i).timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - a draw surface
     */
    public void drawAllOn(DrawSurface d) {
        //List<Sprite> spritesList1 = new ArrayList<Sprite>(this.spritesList);
        for (int i = 0; i < this.spritesList.size(); i++) {
            spritesList.get(i).drawOn(d);
        }
    }

    /**
     * return number of sprites.
     *
     * @return number of sprites
     */
    public int numberOfSprites() {
        return this.spritesList.size();
    }
}

