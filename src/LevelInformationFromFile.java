import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by hadar on 19/06/2017.
 */
public class LevelInformationFromFile implements LevelInformation {
    private Map<String, String> map;
    private List<String> blocklayout = new ArrayList<String>();

    /**
     * constructor.
     *
     * @param map                a map of level information from a file
     * @param blocklayoutOfLevel a list of the strings that represents
     *                           the blocks layout's lines in a level file.
     */
    public LevelInformationFromFile(Map<String, String> map, List<String> blocklayoutOfLevel) {
        this.blocklayout = blocklayoutOfLevel;
        this.map = map;
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        String[] velocities = map.get("ball_velocities").split(" ");
        for (int i = 0; i < velocities.length; i++) {
            String[] v = velocities[i].split(",");
            Velocity velocity = Velocity.fromAngleAndSpeed(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
            list.add(velocity);
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return Integer.parseInt(map.get("paddle_speed"));
    }

    @Override
    public int paddleWidth() {
        return Integer.parseInt(map.get("paddle_width"));
    }

    @Override
    public String levelName() {
        return map.get("level_name");

    }

    @Override
    public Sprite getBackground() {
        Sprite background = null;
        String backgroundString = map.get("background");
        //if background is an image-
        if (backgroundString.startsWith("image")) {
            String imagePath = backgroundString.substring(6, backgroundString.length() - 1);
            System.out.println(imagePath);
            Image img = null;
            try {
                InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
                img = ImageIO.read(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image finalImg = img;
            background = new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    // Draw the image on a DrawSurface
                    d.drawImage(0, 0, finalImg); // draw the image at location 10, 20.
                }

                @Override
                public void timePassed(double dt) {
                    return;
                }
            };
            return background;
        }

        // if the background is a color-
        if (backgroundString.startsWith("color")) {
            String colorString = backgroundString.substring(6, backgroundString.length() - 1);
            // set the color and create a sprite of background
            ColorsParser colorsParser = new ColorsParser();
            Color color = colorsParser.colorFromString(colorString);
            background = new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    d.setColor(color);
                    d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                }

                @Override
                public void timePassed(double dt) {
                    return;
                }
            };
        }
        return background;
    }


    @Override
    public List<Block> blocks() {

        List<Block> blockList = new ArrayList<Block>();
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(map.get("block_definitions"));
        Reader r = new InputStreamReader(is);
        BlocksFromSymbolsFactory factory =
                blocksDefinitionReader.fromReader(r);
        int xpos = Integer.parseInt(map.get("blocks_start_x"));
        int ypos = Integer.parseInt(map.get("blocks_start_y"));
        int rowHeight = Integer.parseInt(map.get("row_height"));
        for (int i = 0; i < this.blocklayout.size(); i++) {
            int x = xpos;
            for (int j = 0; j < this.blocklayout.get(i).length(); j++) {
                String currentSymbol = String.valueOf(blocklayout.get(i).charAt(j));
                if (factory.isSpaceSymbol(currentSymbol)) {
                    x = x + factory.getSpaceWidth(currentSymbol);
                } else {
                    if (factory.isBlockSymbol(currentSymbol)) {
                        Block block = factory.getBlock(currentSymbol, x, ypos);
                        blockList.add(block);
                        x = (int) (x + block.getCollisionRectangle().getWidth());
                    }
                }
            }
            ypos = ypos + rowHeight;

        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return Integer.parseInt(map.get("num_blocks"));
    }
}


