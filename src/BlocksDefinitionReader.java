import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hadar on 17/06/2017.
 */
public class BlocksDefinitionReader {
    /**
     * returns a blockFromSymbols factory witch was created from the reader.
     *
     * @param reader - a block definitions file
     * @return - BlocksFromSymbolsFactory object.(with the two maps)
     */
    public BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] str = stringBuilder.toString().split("\n");
        Map<String, String> defaults = defaults(str);
        BlocksFromSymbolsFactory factory = null;
        try {
            factory = new BlocksFromSymbolsFactory(getSpacerWidths(str), blockCreators(str, defaults));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    /**
     * return a map of the defaults in the reader file.
     *
     * @param strings a strings array of blocks definitions file
     * @return a map of the defaults.
     */
    private Map<String, String> defaults(String[] strings) {
        Map<String, String> map = new TreeMap<String, String>();
        String line;
        for (int i = 0; i < strings.length; i++) {
            line = strings[i];
            if (line.startsWith("default ")) {
                String[] defaultsLine = line.split(" ");
                for (int j = 0; j < defaultsLine.length; j++) {
                    if (defaultsLine[j].contains(":")) {
                        String[] keyVal = defaultsLine[j].split(":");
                        map.put(keyVal[0], keyVal[1]);
                    }
                }
            }
        }
        return map;
    }

    /**
     * return a map that maps a symbol to a block creator according to a block definition file.
     *
     * @param strings    a strings array of blocks definitions file
     * @param defaultMap a strings map of the defaults in the reader file
     * @throws Exception  if fields are missing
     * @return a map that maps a symbol to a block creator
     */
    private Map<String, BlockCreator> blockCreators(String[] strings, Map<String, String> defaultMap) throws Exception {
        Map<String, BlockCreator> resultMap = new TreeMap<String, BlockCreator>();
        String line;
        for (int i = 0; i < strings.length; i++) {
            line = strings[i];
            if (line.startsWith("bdef ")) {
                //create a map for the the current line with the defaults map
                Map<String, String> lineMap = new TreeMap<>();
                lineMap.putAll(defaultMap);

                lineMap.put("fill", null);
                String[] bdef = line.split(" ");
                for (int j = 0; j < bdef.length; j++) {
                    if (bdef[j].contains(":")) {
                        String[] keyVal = bdef[j].split(":");
                        lineMap.put(keyVal[0], keyVal[1]);
                    }
                }
                String blockSymbol = null;
                int blockWidth = 0;
                int blockHeight = 0;
                //get the values of block from the line map-
                try {
                    blockSymbol = lineMap.get("symbol");
                    blockWidth = Integer.parseInt(lineMap.get("width"));
                    blockHeight = Integer.parseInt(lineMap.get("height"));
                } catch (Exception e) {
                    System.out.println("one or more block's fields are missing!");
                    System.exit(-1);
                }
                int blockHitPoints = 1;
                if (lineMap.containsKey("hit_points")) {
                    blockHitPoints = Integer.parseInt(lineMap.get("hit_points"));
                }
                Color blockStroke = null;
                if (lineMap.containsKey("stroke")) {
                    blockStroke = new ColorsParser().colorFromString(
                            lineMap.get("stroke").substring(6, lineMap.get("stroke").length() - 1));
                }
                //create two maps the maps hit point to the fill of the block-
                Map<Integer, Image> hitPointsToImages = new TreeMap<>();
                Map<Integer, Color> hitPointsToColors = new TreeMap<>();
                //the block's fill-
                Color blockColor = null;
                Image blockImage = null;
                if (lineMap.get("fill") != null) {
                    if (lineMap.get("fill").startsWith("color")) {
                        blockColor = new ColorsParser().colorFromString(
                                lineMap.get("fill").substring(6, lineMap.get("fill").length() - 1));
                    } else if (lineMap.get("fill").startsWith("image")) {
                        try {
                            String imagePath = lineMap.get("fill").substring(6, lineMap.get("fill").length() - 1);
                            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
                            blockImage = ImageIO.read(inputStream);

                        } catch (IOException e) {
                            e.printStackTrace();
                            blockImage = null;
                        }
                    }
                }
                hitPointsToColors.put(blockHitPoints, blockColor);
                hitPointsToImages.put(blockHitPoints, blockImage);
                //set the fill-k to the maps-
                if (blockHitPoints > 1) {
                    for (int k = 1; k <= blockHitPoints; k++) {
                        String key = "fill-" + k;
                        if (!lineMap.containsKey(key)) { //if there is no fill-k, use fill
                            hitPointsToColors.put(k, blockColor);
                            hitPointsToImages.put(k, blockImage);
                        } else {
                            Color kColor = null;
                            Image kImage = null;
                            if (lineMap.get(key).startsWith("color")) {
                                kColor = new ColorsParser().colorFromString(
                                        lineMap.get(key).substring(6, lineMap.get(key).length() - 1));
                            } else {
                                if (lineMap.get(key).startsWith("image")) {
                                    try {
                                        String imagePath = lineMap.get(key).
                                                substring(6, lineMap.get(key).length() - 1);
                                        InputStream inputStream = ClassLoader.getSystemClassLoader()
                                                .getResourceAsStream(imagePath);
                                        kImage = ImageIO.read(inputStream);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        kImage = null;
                                    }
                                }
                            }
                            hitPointsToColors.put(k, kColor);
                            hitPointsToImages.put(k, kImage);
                        }
                    }
                    if ((!(lineMap.containsKey("fill-1") || lineMap.containsKey("fill")))) {
                        throw new Exception("fill field is missing");

                    }
                }
                CreateABlock createABlock = new CreateABlock(blockWidth, blockHeight,
                        hitPointsToColors, hitPointsToImages, blockHitPoints, blockStroke);
                resultMap.put(blockSymbol, createABlock);
            }
        }
        return resultMap;
    }

    /**
     * return a map of spacer width.
     * maps a symbol of a spacer to  its width
     *
     * @param strings a strings array of blocks definitions file
     * @return map of spacer width <spaceSymbol,width>
     */
    private Map<String, Integer> getSpacerWidths(String[] strings) {
        Map<String, String> map = new TreeMap<String, String>();
        Map<String, Integer> resultMap = new TreeMap<String, Integer>();
        String line;
        for (int i = 0; i < strings.length; i++) {
            line = strings[i];
            if (line.startsWith("sdef ")) {
                String[] sdef = line.split(" ");
                for (int j = 0; j < sdef.length; j++) {
                    if (sdef[j].contains(":")) {
                        String[] keyVal = sdef[j].split(":");
                        map.put(keyVal[0], keyVal[1]);
                    }
                }
                resultMap.put(map.get("symbol"), Integer.parseInt(map.get("width")));
            }
        }
        return resultMap;
    }
}