import biuoop.DrawSurface;


import java.awt.Color;

/**
 * Created by hadar on 13/06/2017.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * constructor.
     *
     * @param scores - a high score table object
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;


    }

    @Override
        public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0xCE46CF));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText(300, 100, "top " + this.scores.size() + "!", 70);
        d.setColor(Color.black);
        d.drawText(300, 500, "Press space to exit", 20);
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            d.drawText(180, 250 + i * 50, scores.getHighScores().get(i).getName(), 30);
            d.drawText(520, 250 + i * 50, " " + this.scores.getHighScores().get(i).getScore(), 30);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
