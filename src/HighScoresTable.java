
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadar on 11/06/2017.
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> scoreInfoList = new ArrayList<ScoreInfo>();


    /**
     * constructor.
     * Create an empty high-scores table with the specified size.
     *
     * @param size the table holds up to size top scores
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreInfoList = scoreInfoList;
    }


    /**
     * Add a high-score.
     *
     * @param score score info
     */
    public void add(ScoreInfo score) {
        int scoreRank = getRank(score.getScore());
        if (scoreRank > this.size()) {
            System.out.println("not adding");
            return;
        }
        if (this.scoreInfoList.isEmpty()) {
            this.scoreInfoList.add(score);
            for (int j = 0; j < scoreInfoList.size(); j++) {
                System.out.println(scoreInfoList.get(j).getScore());
            }
            return;
        }

        for (int i = 0; i < scoreInfoList.size(); i++) {
            if (score.getScore() > scoreInfoList.get(i).getScore()) {
                this.scoreInfoList.add(scoreRank - 1, score);
                for (int k = 0; k < scoreInfoList.size(); k++) {
                    System.out.println(scoreInfoList.get(k).getScore());
                }
                return;
            }
        }
        this.scoreInfoList.add(score);
        for (int n = 0; n < scoreInfoList.size(); n++) {
            System.out.println(scoreInfoList.get(n).getScore());
        }
    }

    /**
     * Return table size.
     *
     * @return this.size
     */
    public int size() {
        return this.size;
    }


    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return this.scoreInfoList
     */
    public List<ScoreInfo> getHighScores() {
        List<ScoreInfo> highScoreList = new ArrayList<>();
        if (scoreInfoList.size() <= size) {
            highScoreList = scoreInfoList;
        } else {
            for (int i = 0; i < size; i++) {
                highScoreList.add(scoreInfoList.get(i));
            }
        }
        return highScoreList;
    }


    /**
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score a score
     * @return rank of score
     */
    public int getRank(int score) {
        int i = 0;
        for (i = 0; i < scoreInfoList.size(); i++) {
            if (score > scoreInfoList.get(i).getScore()) {
                break;
            }
        }
        System.out.println("rank  " + (i + 1));
        return i + 1;
    }


    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreInfoList.clear();
    }


    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename -file to load from
     * @throws IOException exeception if cant load from file
     */
    public void load(File filename) throws IOException {
        HighScoresTable scoresTable = loadFromFile(filename);
        this.scoreInfoList.clear();
        this.scoreInfoList = scoresTable.scoreInfoList;
    }


    /**
     * Save table data to the specified file.
     *
     * @param filename a file
     * @throws IOException if cant save in this file
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(this.getHighScores());
        } catch (IOException e) {
            System.out.println("cannot save to file");
        } finally {
            if (outputStream != null) { // Exception might have happened at constructor
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
    }


    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename file
     * @return HighScoresTable from file
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable newScoresTable = new HighScoresTable(4);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filename));
            List<ScoreInfo> scoreFromFile = (List<ScoreInfo>) inputStream.readObject();
            if (scoreFromFile != null) {
                //scoreFromFile.clear();
                newScoresTable.scoreInfoList.addAll(scoreFromFile);
            }
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            //return scoresTable;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            //return scoresTable;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            //return scoresTable;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
        return newScoresTable;


    }
}