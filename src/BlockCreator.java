/**
 * Created by hadar on 17/06/2017.
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos upper left x
     * @param ypos upper left right
     * @return a new block
     */
    Block create(int xpos, int ypos);
}


