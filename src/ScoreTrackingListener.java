/**
 * Created by hadar on 26/05/2017.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter counter of score from game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * update the score in an hit event.
     * @param beingHit the block that is being hit
     * @param hitter the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
}


