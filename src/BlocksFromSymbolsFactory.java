
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hadar on 17/06/2017.
 */

public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     *
     * @param spacerWidths  map of symbols to spacers
     * @param blockCreators map of symbols to block creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths,
                                    Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = new TreeMap<>();
        this.spacerWidths = spacerWidths;
        this.blockCreators = new TreeMap<>();
        // this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }


    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s a symbol
     * @return true if 's' is a valid space symbol
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }


    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s symbol
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }


    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s    symbol
     * @param xpos x
     * @param ypos y
     * @return block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        BlockCreator blockCreator = this.blockCreators.get(s);
        return blockCreator.create(xpos, ypos);
    }


    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s symbol
     * @return the width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);

    }
}

