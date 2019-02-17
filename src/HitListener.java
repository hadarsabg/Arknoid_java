/**
 * Created by hadar on 23/05/2017.
 */
public interface HitListener {


    /**
     * called whenever the beingHit object is hit.
     * @param beingHit the block that is being hit
     * @param hitter the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}

