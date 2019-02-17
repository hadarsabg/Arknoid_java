/**
 * Created by hadar on 23/05/2017.
 */
public interface HitNotifier {


    /**
     * Add hl as a listener to hit events.
     * @param hl hit Listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl hit Listener
     */
    void removeHitListener(HitListener hl);
}

