import java.io.Serializable;

/**
 * Created by hadar on 11/06/2017.
 */
public class ScoreInfo implements Serializable {
    private int score;
    private String name;

    /**
     * constructor.
     *
     * @param score -score
     * @param name  - name
     */
    public ScoreInfo(int score, String name) {
        this.score = score;
        this.name = name;
    }

    /**
     * .
     *
     * @return this. score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * .
     *
     * @return this. name
     */
    public String getName() {
        return this.name;
    }
}
