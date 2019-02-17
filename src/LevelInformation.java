
import java.util.List;

/**
 * Created by hadar on 31/05/2017.
 */
public interface LevelInformation {
    /**
     * return number of balls in level.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * return a list of velocities for each ball.
     *
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * return paddle speed.
     *
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * return paddle width.
     *
     * @return paddle Width
     */
    int paddleWidth();


    /**
     * return the name of the level.
     *
     * @return level name
     */
    String levelName();

    /**
     * return a new sprite with the background of the level.
     *
     * @return background
     */
    Sprite getBackground();

    /**
     * return a list of The Blocks that make up this level.
     * each block contains its size, color and location.
     *
     * @return list of blocks
     */
    List<Block> blocks();


    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of blocks to remove
     */
    int numberOfBlocksToRemove();
}

