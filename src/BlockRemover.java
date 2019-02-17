/**
 * Created by hadar on 25/05/2017.
 */

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          - a game
     * @param removedBlocks - the blocks counter from the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * remove from game the blocks that are hit and reach 0 hit-points.
     * also update the remainingBlocks counter,
     * and remove this hit listener from the being hit block.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}

