import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hadar on 19/06/2017.
 */
public class CreateABlock implements BlockCreator {
    private int width;
    private int height;
    private Map<Integer, Color> hitPointsToColors = new TreeMap<>();
    private Map<Integer, Image> hitPointsToImages = new TreeMap<>();
    private int hitPoints;
    private Color stroke = null;

    /**
     * constructor.
     *
     * @param width             block width
     * @param height            block height
     * @param hitPointsToColors maps hit points to colors of block
     * @param hitPointsToImages maps hit points to image fill of block
     * @param hitPoints         hit points
     * @param stroke            color of block stroke
     */
    public CreateABlock(int width, int height, Map<Integer, Color> hitPointsToColors,
                        Map<Integer, Image> hitPointsToImages, int hitPoints, Color stroke) {
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.hitPointsToColors = hitPointsToColors;
        this.hitPointsToImages = hitPointsToImages;
        this.stroke = stroke;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Point pos = new Point(xpos, ypos);
        Rectangle blockRectangle = new Rectangle(pos, this.width, this.height);
        Block b = new Block(blockRectangle, this.hitPoints, this.hitPointsToColors,
                this.hitPointsToImages, this.stroke);
        return b;
        // return new Block(blockRectangle, this.hitPoints, this.hitPointsToColors, this.hitPointsToImages);
    }
}
