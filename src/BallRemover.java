/**
 * Created by hadar on 26/05/2017.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game           a game
     * @param remainingBalls a counter
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }


    /**
     * remove from game the ball that hit the being hit block.
     * also update the game remainingBalls counter.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}

