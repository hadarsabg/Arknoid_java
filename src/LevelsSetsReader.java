import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hadar on 22/06/2017.
 */
public class LevelsSetsReader {
    /**
     * returns a map of level name and symbol to file path from the file.
     * @param reader reader of level sets file
     * @return a map of level name and symbol to file path
     */
    public Map<String, String> levelSetsFromReader(Reader reader) {
        Map<String, String> levelsMap = new TreeMap<>();
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        String line;
        lineNumberReader.setLineNumber(0);
        try {
            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 != 0) {
                    String name = line;
                    line = lineNumberReader.readLine();
                    String filePath = line;
                    levelsMap.put(name, filePath);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelsMap;
    }

}


