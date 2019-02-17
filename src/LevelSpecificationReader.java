
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hadar on 15/06/2017.
 */
public class LevelSpecificationReader {
    /**
     * reads a level definitions file and returns a list of level informations.
     *
     * @param reader a reader of level definitions file
     * @return list of level informations
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInformationList = new ArrayList<LevelInformation>();
        List<String> list = this.splitToLevels(reader);
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> levelMap = this.getLevelSpecificationsMap(list.get(i));
            List<String> blocklayout = blocklayoutOfLevel(list.get(i).split("\n"));
            LevelInformationFromFile levelInformation = new LevelInformationFromFile(levelMap, blocklayout);
            levelInformationList.add(levelInformation);

        }
        return levelInformationList;
    }

    /**
     * split the file in reader to levels and return a list of infos of each level.
     *
     * @param reader a reader
     * @return a list of infos for each level
     */
    public List<String> splitToLevels(java.io.Reader reader) {
        List<String> levelsSpecifications = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {

                StringBuilder stringBuilder = new StringBuilder();
                if (line.equals("START_LEVEL")) {
                    while (!line.equals("END_LEVEL")) {
                        line = bufferedReader.readLine();
                        stringBuilder.append(line + "\n");
                    }
                    levelsSpecifications.add(stringBuilder.toString());
                    line = bufferedReader.readLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelsSpecifications;
    }

    /**
     * return a list of the lines in level specifications that represent the blocks layout.
     *
     * @param levelsSpecifications - an array of lines from START LEVEL to END LEVEL.
     * @return the blocks layOut strings.
     */
    public List<String> blocklayoutOfLevel(String[] levelsSpecifications) {
        List<String> blockslayout = new ArrayList<String>();
        for (int i = 0; i < levelsSpecifications.length; i++) {
            if (levelsSpecifications[i].equals("START_BLOCKS")) {
                int j = i + 1;
                while (!levelsSpecifications[j].equals("END_BLOCKS")) {
                    blockslayout.add(levelsSpecifications[j]);
                    j++;
                }
                break;
            }
        }
        return blockslayout;
    }

    /**
     * a map of the level specifications. all info but the blocks layout.
     *
     * @param string a string of the lines between START LEVEL to END LEVEL
     * @return a map of the level specifications
     */
    public Map<String, String> getLevelSpecificationsMap(String string) {
        Map<String, String> map = new TreeMap<>();
        String[] lines = string.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] splitLine = lines[i].split(":");
            if (splitLine.length == 2) {
                map.put(splitLine[0], splitLine[1]);
            }
        }
        return map;
    }
}